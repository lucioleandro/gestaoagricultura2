package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.EconomicActivityService;
import jakarta.validation.Valid;
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
@RequestMapping("/economic-activities")
public class EconomicActivityController {

    private final EconomicActivityService economicActivityService;

    public EconomicActivityController(EconomicActivityService economicActivityService) {
        this.economicActivityService = economicActivityService;
    }

    @PostMapping
    @CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
    public ResponseEntity<?> cadastraEconomicActivity(@RequestBody @Valid EconomicActivityRequest request) {
        return ResponseEntity.created(null).body(economicActivityService.create(request));
    }

    @PutMapping
    @CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
    public ResponseEntity<?> atualizaEconomicActivity(@RequestBody @Valid EconomicActivityRequest request) {
        return ResponseEntity.ok().body(economicActivityService.atualiza(request));
    }

    @GetMapping
    @Cacheable(value = "listaDeAtividadesEconomicas")
    public List<EconomicActivity> getListaEconomicActivityes() {
        return economicActivityService.findAll();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
    public ResponseEntity<?> removeEconomicActivity(@PathVariable Long id) {
        Optional<EconomicActivity> economicActivity = economicActivityService.findById(id);

        if (economicActivity.isPresent()) {
            economicActivityService.remove(economicActivity.get());
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.notFound().build();
    }

}
