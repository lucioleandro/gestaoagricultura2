package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {
	
	@Autowired
	private CityService municipioService;
	
	@PostMapping("/cadastra")
	@CacheEvict(value = "listaDeMunicipios", allEntries = true)
	public ResponseEntity<?> cadastraMunicipio(@RequestBody br.com.smart4.gestaoagriculturaapi.api.domain.City request) {
		try {
			return ResponseEntity.created(null).body(municipioService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	@CacheEvict(value = "listaDeMunicipios", allEntries = true)
	public ResponseEntity<?> atualizaMunicipio(@RequestBody br.com.smart4.gestaoagriculturaapi.api.domain.City request) {
		try {
			return ResponseEntity.ok().body(municipioService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	@Cacheable(value = "listaDeMunicipios")
	public List<br.com.smart4.gestaoagriculturaapi.api.domain.City> getListaMunicipioes() {
		try {
			return municipioService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	@CacheEvict(value = "listaDeMunicipios", allEntries = true)
	public ResponseEntity<?> removeMunicipio(@PathVariable Long id) {
		try {
			Optional<br.com.smart4.gestaoagriculturaapi.api.domain.City> municipio = municipioService.findById(id);

			if (municipio.isPresent()) {
				municipioService.remove(municipio.get());
				return ResponseEntity.ok().body("");
			} 
			
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
