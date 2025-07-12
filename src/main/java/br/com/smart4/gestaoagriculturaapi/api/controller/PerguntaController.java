package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.Pergunta;
import br.com.smart4.gestaoagriculturaapi.api.domain.RespostaPadrao;
import br.com.smart4.gestaoagriculturaapi.api.service.PerguntaService;
import br.com.smart4.gestaoagriculturaapi.api.service.RespostaPadraoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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
@RequestMapping("/pergunta")
public class PerguntaController {
	
	@Autowired
	private PerguntaService perguntaService;
	
	@Autowired
	private RespostaPadraoService respostaPadraoService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@PostMapping("/cadastra")
	@CacheEvict(value = "{listaDePerguntasAtivas, listaDePerguntas}", allEntries = true )
	public ResponseEntity<?> cadastraPergunta(@Valid @RequestBody Pergunta request) {
		try {
			limpaTodosOsCaches();
			return ResponseEntity.created(null).body(perguntaService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaPergunta(@RequestBody Pergunta request) {
		try {
			limpaTodosOsCaches();
			return ResponseEntity.ok().body(perguntaService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private void limpaTodosOsCaches() {
		cacheManager.getCache("listaDePerguntas").clear();
		cacheManager.getCache("listaDePerguntasAtivas").clear();
	}

	@GetMapping("/lista")
	@Cacheable(value = "listaDePerguntas")
	public List<Pergunta> getListaPerguntas() {
		try {
			return perguntaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listaperguntasativas")
	@Cacheable(value = "listaDePerguntasAtivas")
	public List<Pergunta> getListaPerguntasAtivas() {
		try {
			return perguntaService.findPerguntasAtivas();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removePergunta(@PathVariable Long id) {
		try {
			Optional<Pergunta> pergunta = perguntaService.findById(id);

			if (pergunta.isPresent()) {
				excluiFilhosPergunta(pergunta.get());
				perguntaService.remove(pergunta.get());
				limpaTodosOsCaches();
				
				return ResponseEntity.ok().body("");
			} 
			
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private void excluiFilhosPergunta(Pergunta pergunta) {
		try {
			List<RespostaPadrao> filhos = respostaPadraoService.findByPerguntaId(pergunta.getId());
			
			if(!filhos.isEmpty()) {
				filhos.forEach(filho -> {
					respostaPadraoService.remove(filho);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
