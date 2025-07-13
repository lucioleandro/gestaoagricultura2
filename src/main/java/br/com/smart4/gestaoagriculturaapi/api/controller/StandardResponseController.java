package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.service.StandardResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
@RequestMapping("/respostapadrao")
public class StandardResponseController {

	@Autowired
	private StandardResponseService respostaPadraoService;

	@PostMapping("/cadastra")
	@CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
	public ResponseEntity<?> cadastraStandardResponse(@RequestBody StandardResponse request) {
		try {
			return ResponseEntity.created(null).body(respostaPadraoService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	@CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
	public ResponseEntity<?> atualizaStandardResponse(@RequestBody StandardResponse request) {
		try {
			return ResponseEntity.ok().body(respostaPadraoService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<StandardResponse> getListaStandardResponsees() {
		try {
			return respostaPadraoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/listabypergunta")
	@Cacheable(value = "listaDeRespostasPadroesPorQuestion")
	public List<StandardResponse> getListaStandardResponseByQuestion(@Param(value = "id") Long id) {
		try {
			return respostaPadraoService.findByQuestionId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@DeleteMapping("/remove/{id}")
	@CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
	public ResponseEntity<?> removeStandardResponse(@PathVariable Long id) {
		try {
			Optional<StandardResponse> respostaPadrao = respostaPadraoService.findById(id);

			if (respostaPadrao.isPresent()) {
				respostaPadraoService.remove(respostaPadrao.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
