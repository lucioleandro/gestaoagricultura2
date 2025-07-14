package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.QuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.StandardResponseDTO;
import br.com.smart4.gestaoagriculturaapi.api.services.QuestionService;
import br.com.smart4.gestaoagriculturaapi.api.services.StandardResponseService;
import jakarta.validation.Valid;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @CacheEvict(value = {"listaDeQuestionsAtivas", "listaDeQuestions"}, allEntries = true)
    public ResponseEntity<QuestionResponse> create(@RequestBody @Valid QuestionRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.created(null).body(perguntaService.create(request));
    }

    @PutMapping
    public ResponseEntity<QuestionResponse> update(@RequestBody @Valid QuestionRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.ok().body(perguntaService.update(request));
    }

    @GetMapping
    @Cacheable(value = "listaDeQuestions")
    public ResponseEntity<List<QuestionResponse>> getListaQuestions() {
        return ResponseEntity.ok(perguntaService.findAll());
    }

    @GetMapping("/actives")
    @Cacheable(value = "listaDeQuestionsAtivas")
    public ResponseEntity<List<QuestionResponse>> getList() {
        return ResponseEntity.ok(perguntaService.findQuestionsAtivas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        // TODO: mover essa lógica para o service
//        var pergunta = perguntaService.findById(id);
//
//        if (pergunta.isPresent()) {
//            excluiFilhosQuestion(pergunta.get().getId());
//            perguntaService.remove(pergunta.get());
//            limpaTodosOsCaches();
//            return ResponseEntity.ok().build();
//        }

        return ResponseEntity.notFound().build();
    }

    private void excluiFilhosQuestion(Long perguntaId) {
        //TODO levar lógica para o service
//        List<StandardResponseDTO> filhos = respostaPadraoService.findByQuestionId(perguntaId);
//        filhos.forEach(filho -> respostaPadraoService.remove(filho));
    }

    private void limpaTodosOsCaches() {
        cacheManager.getCache("listaDeQuestions").clear();
        cacheManager.getCache("listaDeQuestionsAtivas").clear();
    }
}
