package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.Endereco;
import br.com.smart4.gestaoagriculturaapi.api.domain.Propriedade;
import br.com.smart4.gestaoagriculturaapi.api.service.EnderecoService;
import br.com.smart4.gestaoagriculturaapi.api.service.PropriedadeService;
import br.com.smart4.gestaoagriculturaapi.api.util.Coordenadas;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.validation.Valid;
import org.glassfish.jersey.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propriedade")
public class PropriedadeController {

	@Autowired
	private PropriedadeService propriedadeService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraPropriedade(@Valid @RequestBody Propriedade request) {
		try {
			Coordenadas coordenadas = this.buscaCoordenadas(request.getEndereco());
			
			request.setLatitude(coordenadas.getLatitude());
			request.setLongitude(coordenadas.getLongitude());
			
			enderecoService.create(request.getEndereco());

			return ResponseEntity.created(null).body(propriedadeService.create(request));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	public Coordenadas buscaCoordenadas(Endereco endereco) {

		HttpClient httpClient = HttpClient.newHttpClient();
		ObjectMapper objectMapper = new ObjectMapper();
//		String apiKey = System.getenv("GOOGLE_MAPS_API_KEY");
		String apiKey = System.getenv("AIzaSyB4hsbTQ9Vsb_2YkIdzJCnjYlM4-IN5Fbo");
		try {
			String enderecoFormatado = String.join(",",
					endereco.getLogradouro(),
					endereco.getNumero(),
					endereco.getBairro().getNome(),
					endereco.getMunicipio().getNome(),
					endereco.getMunicipio().getUf());

			String encodedAddress = URLEncoder.encode(enderecoFormatado, StandardCharsets.UTF_8);
			String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",
					encodedAddress, apiKey);

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.header("Accept", "application/json")
					.GET()
					.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				throw new RuntimeException("Erro ao chamar a API do Google Maps: HTTP " + response.statusCode());
			}

			JsonNode root = objectMapper.readTree(response.body());
			JsonNode location = root.at("/results/0/geometry/location");

			if (location.isMissingNode()) {
				throw new RuntimeException("Não foi possível obter coordenadas para o endereço informado.");
			}

			String lat = location.get("lat").asText();
			String lng = location.get("lng").asText();

			return new Coordenadas(lat, lng);

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar coordenadas: " + e.getMessage(), e);
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaPropriedade(@RequestBody Propriedade request) {
		try {
			Coordenadas coordenadas = this.buscaCoordenadas(request.getEndereco());
			request.setLatitude(coordenadas.getLatitude());
			request.setLongitude(coordenadas.getLongitude());
			
			enderecoService.atualiza(request.getEndereco());
			
			return ResponseEntity.ok().body(propriedadeService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<Propriedade> getListaPropriedadees() {
		try {
			return propriedadeService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyagricultor")
	public List<Propriedade> getListaPropriedadeesByAgricultor(@Param(value = "id") Long id) {
		try {
			return propriedadeService.findByAgricultor(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removePropriedade(@PathVariable Long id) {
		try {
			Optional<Propriedade> propriedade = propriedadeService.findById(id);
			
			propriedade.ifPresent(prop -> {
				Optional<Endereco> endereco = enderecoService.findById(prop.getEndereco().getId());
				endereco.ifPresent(end -> enderecoService.remove(end));
				propriedadeService.remove(prop);
			});
	
			return ResponseEntity.ok().body("");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}

