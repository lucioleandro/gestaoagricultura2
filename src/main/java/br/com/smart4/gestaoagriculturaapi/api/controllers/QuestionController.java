package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.QuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.QuestionService;
import br.com.smart4.gestaoagriculturaapi.api.services.StandardResponseService;
import jakarta.validation.Valid;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService perguntaService;

    private final StandardResponseService respostaPadraoService;

    private final CacheManager cacheManager;

    public QuestionController(QuestionService perguntaService, StandardResponseService respostaPadraoService, CacheManager cacheManager) {
        this.perguntaService = perguntaService;
        this.respostaPadraoService = respostaPadraoService;
        this.cacheManager = cacheManager;
    }

    @PostMapping
    @CacheEvict(value = "{listaDeQuestionsAtivas, listaDeQuestions}", allEntries = true)
    public ResponseEntity<?> create(@RequestBody @Valid QuestionRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.created(null).body(perguntaService.create(request));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid QuestionRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.ok().body(perguntaService.update(request));
    }

    private void limpaTodosOsCaches() {
        cacheManager.getCache("listaDeQuestions").clear();
        cacheManager.getCache("listaDeQuestionsAtivas").clear();
    }

    @GetMapping
    @Cacheable(value = "listaDeQuestions")
    public List<Question> getListaQuestions() {
        return perguntaService.findAll();
    }

    @GetMapping("/actives")
    @Cacheable(value = "listaDeQuestionsAtivas")
    public List<Question> getList() {
        return perguntaService.findQuestionsAtivas();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Question> pergunta = perguntaService.findById(id);

        if (pergunta.isPresent()) {
            excluiFilhosQuestion(pergunta.get());
            perguntaService.remove(pergunta.get());
            limpaTodosOsCaches();

            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.notFound().build();
    }

    private void excluiFilhosQuestion(Question pergunta) {
        List<StandardResponse> filhos = respostaPadraoService.findByQuestionId(pergunta.getId());

        if (!filhos.isEmpty()) {
            filhos.forEach(filho -> {
                respostaPadraoService.remove(filho);
            });
        }
    }

}
