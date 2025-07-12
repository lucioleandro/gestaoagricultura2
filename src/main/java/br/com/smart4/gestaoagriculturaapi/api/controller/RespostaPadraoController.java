package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.RespostaPadrao;
import br.com.smart4.gestaoagriculturaapi.api.service.RespostaPadraoService;
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
public class RespostaPadraoController {

	@Autowired
	private RespostaPadraoService respostaPadraoService;

	@PostMapping("/cadastra")
	@CacheEvict(value = "listaDeRespostasPadroesPorPergunta", allEntries = true)
	public ResponseEntity<?> cadastraRespostaPadrao(@RequestBody RespostaPadrao request) {
		try {
			return ResponseEntity.created(null).body(respostaPadraoService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	@CacheEvict(value = "listaDeRespostasPadroesPorPergunta", allEntries = true)
	public ResponseEntity<?> atualizaRespostaPadrao(@RequestBody RespostaPadrao request) {
		try {
			return ResponseEntity.ok().body(respostaPadraoService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<RespostaPadrao> getListaRespostaPadraoes() {
		try {
			return respostaPadraoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/listabypergunta")
	@Cacheable(value = "listaDeRespostasPadroesPorPergunta")
	public List<RespostaPadrao> getListaRespostaPadraoByPergunta(@Param(value = "id") Long id) {
		try {
			return respostaPadraoService.findByPerguntaId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@DeleteMapping("/remove/{id}")
	@CacheEvict(value = "listaDeRespostasPadroesPorPergunta", allEntries = true)
	public ResponseEntity<?> removeRespostaPadrao(@PathVariable Long id) {
		try {
			Optional<RespostaPadrao> respostaPadrao = respostaPadraoService.findById(id);

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
