package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.StandardResponseRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.StandardResponseService;
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
    public ResponseEntity<?> cadastraStandardResponse(@RequestBody @Valid StandardResponseRequest request) {
        return ResponseEntity.created(null).body(respostaPadraoService.create(request));
    }

    @PutMapping
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<?> atualizaStandardResponse(@RequestBody @Valid StandardResponseRequest request) {
        return ResponseEntity.ok().body(respostaPadraoService.atualiza(request));
    }

    @GetMapping
    public List<StandardResponse> getListaStandardResponsees() {
        return respostaPadraoService.findAll();
    }

    @GetMapping("/question")
    @Cacheable(value = "listaDeRespostasPadroesPorQuestion")
    public List<StandardResponse> getListaStandardResponseByQuestion(@Param(value = "id") Long questionId) {
        return respostaPadraoService.findByQuestionId(questionId);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<?> removeStandardResponse(@PathVariable Long id) {
        Optional<StandardResponse> respostaPadrao = respostaPadraoService.findById(id);

        if (respostaPadrao.isPresent()) {
            respostaPadraoService.remove(respostaPadrao.get());
            return ResponseEntity.ok().body("");
        }

        return ResponseEntity.notFound().build();
    }
}
