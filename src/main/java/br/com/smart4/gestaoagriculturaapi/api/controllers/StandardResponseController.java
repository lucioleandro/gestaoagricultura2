package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.StandardResponseService;
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
@RequestMapping("/respostapadrao")
public class StandardResponseController {

    private final StandardResponseService respostaPadraoService;

    public StandardResponseController(StandardResponseService respostaPadraoService) {
        this.respostaPadraoService = respostaPadraoService;
    }

    @PostMapping("/cadastra")
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<?> cadastraStandardResponse(@RequestBody StandardResponse request) {
        return ResponseEntity.created(null).body(respostaPadraoService.create(request));
    }

    @PutMapping("/atualiza")
    @CacheEvict(value = "listaDeRespostasPadroesPorQuestion", allEntries = true)
    public ResponseEntity<?> atualizaStandardResponse(@RequestBody StandardResponse request) {
        return ResponseEntity.ok().body(respostaPadraoService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<StandardResponse> getListaStandardResponsees() {
        return respostaPadraoService.findAll();
    }

    @GetMapping("/listabypergunta")
    @Cacheable(value = "listaDeRespostasPadroesPorQuestion")
    public List<StandardResponse> getListaStandardResponseByQuestion(@Param(value = "id") Long id) {
        return respostaPadraoService.findByQuestionId(id);
    }

    @DeleteMapping("/remove/{id}")
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
