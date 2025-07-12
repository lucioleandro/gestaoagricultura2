package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadeEconomica;
import br.com.smart4.gestaoagriculturaapi.api.service.AtividadeEconomicaService;
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
@RequestMapping("/atividadeeconomica")
public class AtividadeEconomicaController {

	@Autowired
	private AtividadeEconomicaService atividadeEconomicaService;
	
	@PostMapping("/cadastra")
	@CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
	public ResponseEntity<?> cadastraAtividadeEconomica(@RequestBody AtividadeEconomica request) {
		try {
			return ResponseEntity.created(null).body(atividadeEconomicaService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	@CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
	public ResponseEntity<?> atualizaAtividadeEconomica(@RequestBody AtividadeEconomica request) {
		try {
			return ResponseEntity.ok().body(atividadeEconomicaService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	@Cacheable(value = "listaDeAtividadesEconomicas")
	public List<AtividadeEconomica> getListaAtividadeEconomicaes() {
		try {
			return atividadeEconomicaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	@CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
	public ResponseEntity<?> removeAtividadeEconomica(@PathVariable Long id) {
		try {
			Optional<AtividadeEconomica> atividadeEconomica = atividadeEconomicaService.findById(id);

			if (atividadeEconomica.isPresent()) {
				atividadeEconomicaService.remove(atividadeEconomica.get());
				return ResponseEntity.ok().body("");
			} 
			
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
