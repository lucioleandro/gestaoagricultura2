package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.Bairro;
import br.com.smart4.gestaoagriculturaapi.api.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bairro")
public class BairroController {
	
	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private CacheManager cacheManager;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraBairro(@RequestBody Bairro request) {
		try {
			limpaTodosOsCaches();
			return ResponseEntity.created(null).body(bairroService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaBairro(@RequestBody Bairro request) {
		try {
			limpaTodosOsCaches();
			return ResponseEntity.ok().body(bairroService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private void limpaTodosOsCaches() {
		cacheManager.getCache("listaDeBairros").clear();
		cacheManager.getCache("listaDeBairrosPorMunicipio").clear();
	}

	@GetMapping("/lista")
	@Cacheable(value = "listaDeBairros")
	public List<Bairro> getListaBairros() {
		try {
			return bairroService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabymunicipio")
	@Cacheable(value = "listaDeBairrosPorMunicipio")
	public List<Bairro> getListaBairrosByMunicipio(@Param(value = "municipio") String municipio) {
		try {
			return bairroService.findByMunicipioNome(municipio);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeBairro(@PathVariable Long id) {
		try {
			Optional<Bairro> bairro = bairroService.findById(id);

			if (bairro.isPresent()) {
				bairroService.remove(bairro.get());
				limpaTodosOsCaches();
				return ResponseEntity.ok().body("");
			}

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
