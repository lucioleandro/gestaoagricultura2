package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.service.EconomicActivityService;
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
@RequestMapping("/economicactivity")
public class EconomicActivityController {

	@Autowired
	private EconomicActivityService economicActivityService;
	
	@PostMapping("/cadastra")
	@CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
	public ResponseEntity<?> cadastraEconomicActivity(@RequestBody EconomicActivity request) {
		try {
			return ResponseEntity.created(null).body(economicActivityService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	@CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
	public ResponseEntity<?> atualizaEconomicActivity(@RequestBody EconomicActivity request) {
		try {
			return ResponseEntity.ok().body(economicActivityService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	@Cacheable(value = "listaDeAtividadesEconomicas")
	public List<EconomicActivity> getListaEconomicActivityes() {
		try {
			return economicActivityService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	@CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
	public ResponseEntity<?> removeEconomicActivity(@PathVariable Long id) {
		try {
			Optional<EconomicActivity> economicActivity = economicActivityService.findById(id);

			if (economicActivity.isPresent()) {
				economicActivityService.remove(economicActivity.get());
				return ResponseEntity.ok().body("");
			} 
			
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
