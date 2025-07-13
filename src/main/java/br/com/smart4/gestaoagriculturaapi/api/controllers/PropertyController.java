package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.services.AddressService;
import br.com.smart4.gestaoagriculturaapi.api.services.PropertyService;
import br.com.smart4.gestaoagriculturaapi.api.utils.Coordinates;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    private final AddressService addressService;

    public PropertyController(PropertyService propertyService, AddressService addressService) {
        this.propertyService = propertyService;
        this.addressService = addressService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraProperty(@Valid @RequestBody Property request) {
        Coordinates coordenadas = this.buscaCoordenadas(request.getAddress());

        request.setLatitude(coordenadas.getLatitude());
        request.setLongitude(coordenadas.getLongitude());

        addressService.create(request.getAddress());

        return ResponseEntity.created(null).body(propertyService.create(request));
    }

    public Coordinates buscaCoordenadas(Address address) {

        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
//		String apiKey = System.getenv("GOOGLE_MAPS_API_KEY");
        String apiKey = System.getenv("AIzaSyB4hsbTQ9Vsb_2YkIdzJCnjYlM4-IN5Fbo");
        try {
            String addressFormatado = String.join(",",
                    address.getLogradouro(),
                    address.getNumero(),
                    address.getNeighborhood().getNome(),
                    address.getCity().getNome(),
                    address.getCity().getUf());

            String encodedAddress = URLEncoder.encode(addressFormatado, StandardCharsets.UTF_8);
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

            return new Coordinates(lat, lng);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar coordenadas: " + e.getMessage(), e);
        }
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaProperty(@RequestBody Property request) {
        Coordinates coordenadas = this.buscaCoordenadas(request.getAddress());
        request.setLatitude(coordenadas.getLatitude());
        request.setLongitude(coordenadas.getLongitude());

        addressService.atualiza(request.getAddress());

        return ResponseEntity.ok().body(propertyService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<Property> getListaPropertyes() {
        return propertyService.findAll();
    }

    @GetMapping("/listabyfarmer")
    public List<Property> getListaPropertyesByFarmer(@Param(value = "id") Long id) {
        return propertyService.findByFarmer(id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeProperty(@PathVariable Long id) {
        Optional<Property> property = propertyService.findById(id);

        property.ifPresent(prop -> {
            Optional<Address> address = addressService.findById(prop.getAddress().getId());
            address.ifPresent(end -> addressService.remove(end));
            propertyService.remove(prop);
        });

        return ResponseEntity.ok().body("");
    }
}

