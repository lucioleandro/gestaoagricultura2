package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.CityRequest;
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
@RequestMapping("/city")
public class CityController {

    private final CityService municipioService;

    public CityController(CityService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping("/cadastra")
    @CacheEvict(value = "listaDeMunicipios", allEntries = true)
    public ResponseEntity<?> cadastraMunicipio(@RequestBody @Valid CityRequest request) {
        return ResponseEntity.created(null).body(municipioService.create(request));
    }

    @PutMapping("/atualiza")
    @CacheEvict(value = "listaDeMunicipios", allEntries = true)
    public ResponseEntity<?> atualizaMunicipio(@RequestBody @Valid CityRequest request) {
        return ResponseEntity.ok().body(municipioService.atualiza(request));
    }

    @GetMapping("/lista")
    @Cacheable(value = "listaDeMunicipios")
    public List<City> getListaMunicipioes() {
        return municipioService.findAll();
    }

    @DeleteMapping("/remove/{id}")
    @CacheEvict(value = "listaDeMunicipios", allEntries = true)
    public ResponseEntity<?> removeMunicipio(@PathVariable Long id) {
        Optional<City> municipio = municipioService.findById(id);
        if (municipio.isPresent()) {
            municipioService.remove(municipio.get());
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.notFound().build();
    }

}
