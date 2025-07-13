package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.service.NeighborhoodService;
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
@RequestMapping("/neighborhood")
public class NeighborhoodController {
	
	@Autowired
	private NeighborhoodService neighborhoodService;
	
	@Autowired
	private CacheManager cacheManager;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraNeighborhood(@RequestBody Neighborhood request) {
		try {
			limpaTodosOsCaches();
			return ResponseEntity.created(null).body(neighborhoodService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaNeighborhood(@RequestBody Neighborhood request) {
		try {
			limpaTodosOsCaches();
			return ResponseEntity.ok().body(neighborhoodService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private void limpaTodosOsCaches() {
		cacheManager.getCache("listaDeNeighborhoods").clear();
		cacheManager.getCache("listaDeNeighborhoodsPorMunicipio").clear();
	}

	@GetMapping("/lista")
	@Cacheable(value = "listaDeNeighborhoods")
	public List<Neighborhood> getListaNeighborhoods() {
		try {
			return neighborhoodService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabymunicipio")
	@Cacheable(value = "listaDeNeighborhoodsPorMunicipio")
	public List<Neighborhood> getListaNeighborhoodsByMunicipio(@Param(value = "municipio") String municipio) {
		try {
			return neighborhoodService.findByMunicipioNome(municipio);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeNeighborhood(@PathVariable Long id) {
		try {
			Optional<Neighborhood> neighborhood = neighborhoodService.findById(id);

			if (neighborhood.isPresent()) {
				neighborhoodService.remove(neighborhood.get());
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
