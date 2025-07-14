package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.NeighborhoodRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.NeighborhoodResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.NeighborhoodService;
import jakarta.validation.Valid;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;
    private final CacheManager cacheManager;

    public NeighborhoodController(NeighborhoodService neighborhoodService, CacheManager cacheManager) {
        this.neighborhoodService = neighborhoodService;
        this.cacheManager = cacheManager;
    }

    @PostMapping
    public ResponseEntity<NeighborhoodResponse> create(@RequestBody @Valid NeighborhoodRequest request) {
        limpaTodosOsCaches();
        NeighborhoodResponse response = neighborhoodService.create(request);
        return ResponseEntity.created(null).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NeighborhoodResponse> update(@PathVariable Long id, @RequestBody @Valid NeighborhoodRequest request) {
        limpaTodosOsCaches();
        NeighborhoodResponse response = neighborhoodService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Cacheable(value = "listaDeNeighborhoods")
    public ResponseEntity<List<NeighborhoodResponse>> getList() {
        List<NeighborhoodResponse> responses = neighborhoodService.findAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/city")
    @Cacheable(value = "listaDeNeighborhoodsPorMunicipio")
    public ResponseEntity<List<NeighborhoodResponse>> getListByCity(@Param("city") String municipio) {
        List<NeighborhoodResponse> responses = neighborhoodService.findByMunicipioNome(municipio);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        Optional<NeighborhoodResponse> neighborhood = neighborhoodService.findById(id);
//
//        if (neighborhood.isPresent()) {
//            // TODO: mover lógica de remoção para o service
//            neighborhoodService.removeById(id);
//            limpaTodosOsCaches();
//            return ResponseEntity.ok().build();
//        }

        return ResponseEntity.notFound().build();
    }

    private void limpaTodosOsCaches() {
        cacheManager.getCache("listaDeNeighborhoods").clear();
        cacheManager.getCache("listaDeNeighborhoodsPorMunicipio").clear();
    }
}
