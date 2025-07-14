package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AnsweredQuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.QuestionResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Tag(name = "Question Responses", description = "Endpoints for responding to questions")
@RestController
@RequestMapping("/question-responses")
public class QuestionResponseController {

    private final QuestionResponseService respostaQuestionService;

    public QuestionResponseController(QuestionResponseService respostaQuestionService) {
        this.respostaQuestionService = respostaQuestionService;
    }

    @Operation(summary = "Answer a question", description = "Creates a new answer to a question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Answer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<AnsweredQuestionResponse> create(@RequestBody @Valid ResponseQuestionRequest request) {
        AnsweredQuestionResponse created = respostaQuestionService.create(request);
        return ResponseEntity.created(URI.create("/question-responses/" + created.getId())).body(created);
    }

    @Operation(summary = "Answer multiple questions", description = "Saves answers for multiple questions at once")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answers saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/list")
    public ResponseEntity<List<AnsweredQuestionResponse>> createList(@RequestBody @Valid List<ResponseQuestionRequest> request) {
        List<AnsweredQuestionResponse> responses = request.stream()
                .map(respostaQuestionService::create)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Update a question answer", description = "Updates an existing answer based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Answer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnsweredQuestionResponse> update(@PathVariable Long id, @RequestBody @Valid ResponseQuestionRequest request) {
        return ResponseEntity.ok(respostaQuestionService.update(id, request));
    }

    @Operation(summary = "List all answers", description = "Returns all question answers in the system")
    @ApiResponse(responseCode = "200", description = "Answers retrieved successfully")
    @GetMapping
    public ResponseEntity<List<AnsweredQuestionResponse>> getList() {
        return ResponseEntity.ok(respostaQuestionService.findAll());
    }

    @Operation(summary = "List answers by farmer", description = "Returns all answers submitted by a specific farmer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answers retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Farmer not found")
    })
    @GetMapping("/farmer")
    public ResponseEntity<List<AnsweredQuestionResponse>> getListByQuestion(
            @Parameter(description = "Farmer ID") @Param("id") Long farmerId) {
        return ResponseEntity.ok(respostaQuestionService.findByFarmerId(farmerId));
    }

    @Operation(summary = "Delete an answer", description = "Deletes an answer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Answer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Answer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        respostaQuestionService.remove(id);
        return ResponseEntity.notFound().build();
    }
}
