package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.StandardResponseRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.StandardResponseDTO;
import br.com.smart4.gestaoagriculturaapi.api.services.StandardResponseService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/standard-responses")
public class StandardResponseController {

    private final StandardResponseService respostaPadraoService;

    public StandardResponseController(StandardResponseService respostaPadraoService) {
        this.respostaPadraoService = respostaPadraoService;
    }

    @PostMapping
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<StandardResponseDTO> create(@RequestBody @Valid StandardResponseRequest request) {
        StandardResponseDTO created = respostaPadraoService.create(request);
        return ResponseEntity.created(URI.create("/standard-responses/" + created.getId())).body(created);
    }

    @PutMapping
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<StandardResponseDTO> update(@RequestBody @Valid StandardResponseRequest request) {
        return ResponseEntity.ok().body(respostaPadraoService.update(request));
    }

    @GetMapping
    public ResponseEntity<List<StandardResponseDTO>> getList() {
        return ResponseEntity.ok(respostaPadraoService.findAll());
    }

    @GetMapping("/question")
    @Cacheable(value = "listaDeRespostasPadroesPorQuestion")
    public ResponseEntity<List<StandardResponseDTO>> getListByQuestion(@Param("id") Long questionId) {
        return ResponseEntity.ok(respostaPadraoService.findByQuestionId(questionId));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        // TODO: mover essa lógica de verificação para dentro do service
//        Optional<StandardResponseDTO> respostaPadrao = respostaPadraoService.findById(id);
//
//        if (respostaPadrao.isPresent()) {
//            respostaPadraoService.remove(respostaPadrao.get());
//            return ResponseEntity.ok().build();
//        }

        return ResponseEntity.notFound().build();
    }
}
