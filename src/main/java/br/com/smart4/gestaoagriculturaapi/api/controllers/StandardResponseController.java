package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.StandardResponseRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.StandardResponseDTO;
import br.com.smart4.gestaoagriculturaapi.api.services.StandardResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

@Tag(name = "Standard Responses", description = "Endpoints for managing standard answers to questions")
@RestController
@RequestMapping("/standard-responses")
public class StandardResponseController {

    private final StandardResponseService respostaPadraoService;

    public StandardResponseController(StandardResponseService respostaPadraoService) {
        this.respostaPadraoService = respostaPadraoService;
    }

    @Operation(summary = "Create standard response", description = "Registers a new standard response linked to a question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Standard response created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PostMapping
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<StandardResponseDTO> create(@RequestBody @Valid StandardResponseRequest request) {
        StandardResponseDTO created = respostaPadraoService.create(request);
        return ResponseEntity.created(URI.create("/standard-responses/" + created.getId())).body(created);
    }

    @Operation(summary = "Update standard response", description = "Updates a standard response based on the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Standard response updated successfully"),
            @ApiResponse(responseCode = "404", description = "Standard response not found")
    })
    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<StandardResponseDTO> update(@PathVariable Long id, @RequestBody @Valid StandardResponseRequest request) {
        return ResponseEntity.ok().body(respostaPadraoService.update(id, request));
    }

    @Operation(summary = "List all standard responses", description = "Returns all standard responses registered in the system")
    @ApiResponse(responseCode = "200", description = "List returned successfully")
    @GetMapping
    public ResponseEntity<List<StandardResponseDTO>> getList() {
        return ResponseEntity.ok(respostaPadraoService.findAll());
    }

    @Operation(summary = "List standard responses by question", description = "Returns standard responses linked to a specific question")
    @ApiResponse(responseCode = "200", description = "List returned successfully")
    @GetMapping("/question")
    @Cacheable(value = "listaDeRespostasPadroesPorQuestion")
    public ResponseEntity<List<StandardResponseDTO>> getListByQuestion(
            @Parameter(description = "Question ID") @Param("id") Long questionId) {
        return ResponseEntity.ok(respostaPadraoService.findByQuestionId(questionId));
    }

    @Operation(summary = "Delete a standard response", description = "Deletes a standard response by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Standard response deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Standard response not found")
    })
    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        respostaPadraoService.remove(id);
        return ResponseEntity.notFound().build();
    }
}
