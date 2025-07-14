package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.CityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.CityResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.CityService;
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
@RequestMapping("/cities")
public class CityController {

    private final CityService municipioService;

    public CityController(CityService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping
    @CacheEvict(value = "listaDeMunicipios", allEntries = true)
    public ResponseEntity<CityResponse> create(@RequestBody @Valid CityRequest request) {
        return ResponseEntity.created(null).body(municipioService.create(request));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeMunicipios", allEntries = true)
    public ResponseEntity<CityResponse> update(@PathVariable Long id, @RequestBody @Valid CityRequest request) {
        return ResponseEntity.ok().body(municipioService.update(id, request));
    }

    @GetMapping
    @Cacheable(value = "listaDeMunicipios")
    public ResponseEntity<List<CityResponse>> getList() {
        return ResponseEntity.ok(municipioService.findAll());
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeMunicipios", allEntries = true)
    public ResponseEntity<CityResponse> remove(@PathVariable Long id) {
//        Optional<CityResponse> municipio = municipioService.findById(id);
//        if (municipio.isPresent()) {
//            municipioService.remove(municipio.get());
//            return ResponseEntity.ok().body("");
//        }
        // TODO: Levar lógica para os service
        return ResponseEntity.notFound().build();
    }

}
