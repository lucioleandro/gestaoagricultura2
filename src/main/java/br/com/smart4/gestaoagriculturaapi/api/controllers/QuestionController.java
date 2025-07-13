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
@RequestMapping("/pergunta")
public class QuestionController {

    private final QuestionService perguntaService;

    private final StandardResponseService respostaPadraoService;

    private final CacheManager cacheManager;

    public QuestionController(QuestionService perguntaService, StandardResponseService respostaPadraoService, CacheManager cacheManager) {
        this.perguntaService = perguntaService;
        this.respostaPadraoService = respostaPadraoService;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/cadastra")
    @CacheEvict(value = "{listaDeQuestionsAtivas, listaDeQuestions}", allEntries = true)
    public ResponseEntity<?> cadastraQuestion(@RequestBody @Valid QuestionRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.created(null).body(perguntaService.create(request));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaQuestion(@RequestBody @Valid QuestionRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.ok().body(perguntaService.atualiza(request));
    }

    private void limpaTodosOsCaches() {
        cacheManager.getCache("listaDeQuestions").clear();
        cacheManager.getCache("listaDeQuestionsAtivas").clear();
    }

    @GetMapping("/lista")
    @Cacheable(value = "listaDeQuestions")
    public List<Question> getListaQuestions() {
        return perguntaService.findAll();
    }

    @GetMapping("/listaperguntasativas")
    @Cacheable(value = "listaDeQuestionsAtivas")
    public List<Question> getListaQuestionsAtivas() {
        return perguntaService.findQuestionsAtivas();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeQuestion(@PathVariable Long id) {
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
