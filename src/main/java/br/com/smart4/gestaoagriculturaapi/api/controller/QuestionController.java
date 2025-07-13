package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.Question;
import br.com.smart4.gestaoagriculturaapi.api.domain.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.service.QuestionService;
import br.com.smart4.gestaoagriculturaapi.api.service.StandardResponseService;
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
public class QuestionController {
	
	@Autowired
	private QuestionService perguntaService;
	
	@Autowired
	private StandardResponseService respostaPadraoService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@PostMapping("/cadastra")
	@CacheEvict(value = "{listaDeQuestionsAtivas, listaDeQuestions}", allEntries = true )
	public ResponseEntity<?> cadastraQuestion(@Valid @RequestBody Question request) {
		try {
			limpaTodosOsCaches();
			return ResponseEntity.created(null).body(perguntaService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaQuestion(@RequestBody Question request) {
		try {
			limpaTodosOsCaches();
			return ResponseEntity.ok().body(perguntaService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private void limpaTodosOsCaches() {
		cacheManager.getCache("listaDeQuestions").clear();
		cacheManager.getCache("listaDeQuestionsAtivas").clear();
	}

	@GetMapping("/lista")
	@Cacheable(value = "listaDeQuestions")
	public List<Question> getListaQuestions() {
		try {
			return perguntaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listaperguntasativas")
	@Cacheable(value = "listaDeQuestionsAtivas")
	public List<Question> getListaQuestionsAtivas() {
		try {
			return perguntaService.findQuestionsAtivas();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeQuestion(@PathVariable Long id) {
		try {
			Optional<Question> pergunta = perguntaService.findById(id);

			if (pergunta.isPresent()) {
				excluiFilhosQuestion(pergunta.get());
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
	
	private void excluiFilhosQuestion(Question pergunta) {
		try {
			List<StandardResponse> filhos = respostaPadraoService.findByQuestionId(pergunta.getId());
			
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
