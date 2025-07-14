package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.QuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.QuestionService;
import br.com.smart4.gestaoagriculturaapi.api.services.StandardResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Question", description = "Endpoints for managing system questions")
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService perguntaService;
    private final CacheManager cacheManager;
    private final QuestionService questionService;

    public QuestionController(QuestionService perguntaService, CacheManager cacheManager, QuestionService questionService) {
        this.perguntaService = perguntaService;
        this.cacheManager = cacheManager;
        this.questionService = questionService;
    }

    @Operation(summary = "Create a question", description = "Registers a new question in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Question created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    @CacheEvict(value = {"listaDeQuestionsAtivas", "listaDeQuestions"}, allEntries = true)
    public ResponseEntity<QuestionResponse> create(
            @RequestBody @Valid QuestionRequest request) {

        limpaTodosOsCaches();
        QuestionResponse created = perguntaService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Update a question", description = "Updates question data based on the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Question not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> update(@PathVariable Long id, @RequestBody @Valid QuestionRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.ok().body(perguntaService.update(id, request));
    }

    @Operation(summary = "List all questions", description = "Returns a list of all questions in the system")
    @ApiResponse(responseCode = "200", description = "Questions retrieved successfully")
    @GetMapping
    @Cacheable(value = "listaDeQuestions")
    public ResponseEntity<List<QuestionResponse>> getListaQuestions() {
        return ResponseEntity.ok(perguntaService.findAll());
    }

    @Operation(summary = "List active questions", description = "Returns only questions marked as active")
    @ApiResponse(responseCode = "200", description = "Active questions retrieved successfully")
    @GetMapping("/actives")
    @Cacheable(value = "listaDeQuestionsAtivas")
    public ResponseEntity<List<QuestionResponse>> getList() {
        return ResponseEntity.ok(perguntaService.findQuestionsAtivas());
    }

    @Operation(summary = "Delete a question", description = "Deletes a question by ID, along with its child responses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Question not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        questionService.remove(id);
        return ResponseEntity.notFound().build();
    }

    private void limpaTodosOsCaches() {
        cacheManager.getCache("listaDeQuestions").clear();
        cacheManager.getCache("listaDeQuestionsAtivas").clear();
    }
}
