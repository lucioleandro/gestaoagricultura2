package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AnsweredQuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.QuestionResponseService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question-responses")
public class QuestionResponseController {

    private final QuestionResponseService respostaQuestionService;

    public QuestionResponseController(QuestionResponseService respostaQuestionService) {
        this.respostaQuestionService = respostaQuestionService;
    }

    @PostMapping
    public ResponseEntity<AnsweredQuestionResponse> create(@RequestBody @Valid ResponseQuestionRequest request) {
        AnsweredQuestionResponse created = respostaQuestionService.create(request);
        return ResponseEntity.created(URI.create("/question-responses/" + created.getId())).body(created);
    }

    @PostMapping("/list")
    public ResponseEntity<List<AnsweredQuestionResponse>> createList(@RequestBody @Valid List<ResponseQuestionRequest> request) {
        // TODO revisar isso aqui - avaliar necessidade de limpar respostas anteriores do farmer

        List<AnsweredQuestionResponse> responses = request.stream()
                .map(respostaQuestionService::create)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnsweredQuestionResponse> update(@PathVariable Long id, @RequestBody @Valid ResponseQuestionRequest request) {
        return ResponseEntity.ok(respostaQuestionService.update(id, request));
    }

    @GetMapping
    public ResponseEntity<List<AnsweredQuestionResponse>> getList() {
        return ResponseEntity.ok(respostaQuestionService.findAll());
    }

    @GetMapping("/farmer")
    public ResponseEntity<List<AnsweredQuestionResponse>> getListByQuestion(@Param("id") Long farmerId) {
        return ResponseEntity.ok(respostaQuestionService.findByFarmerId(farmerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        // TODO: mover essa lógica para o service
//        Optional<AnsweredQuestionResponse> respostaQuestion = respostaQuestionService.findById(id);
//
//        if (respostaQuestion.isPresent()) {
//            respostaQuestionService.remove(respostaQuestion.get());
//            return ResponseEntity.ok().build();
//        }

        return ResponseEntity.notFound().build();
    }
}
