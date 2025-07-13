package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.NeighborhoodRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.NeighborhoodService;
import jakarta.validation.Valid;
import org.springframework.cache.CacheManager;
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
@RequestMapping("/neighborhood")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;

    private final CacheManager cacheManager;

    public NeighborhoodController(NeighborhoodService neighborhoodService, CacheManager cacheManager) {
        this.neighborhoodService = neighborhoodService;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraNeighborhood(@RequestBody @Valid NeighborhoodRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.created(null).body(neighborhoodService.create(request));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaNeighborhood(@RequestBody @Valid NeighborhoodRequest request) {
        limpaTodosOsCaches();
        return ResponseEntity.ok().body(neighborhoodService.atualiza(request));
    }

    private void limpaTodosOsCaches() {
        cacheManager.getCache("listaDeNeighborhoods").clear();
        cacheManager.getCache("listaDeNeighborhoodsPorMunicipio").clear();
    }

    @GetMapping("/lista")
    @Cacheable(value = "listaDeNeighborhoods")
    public List<Neighborhood> getListaNeighborhoods() {
        return neighborhoodService.findAll();
    }

    @GetMapping("/listabymunicipio")
    @Cacheable(value = "listaDeNeighborhoodsPorMunicipio")
    public List<Neighborhood> getListaNeighborhoodsByMunicipio(@Param(value = "municipio") String municipio) {
        return neighborhoodService.findByMunicipioNome(municipio);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeNeighborhood(@PathVariable Long id) {
        Optional<Neighborhood> neighborhood = neighborhoodService.findById(id);

        if (neighborhood.isPresent()) {
            neighborhoodService.remove(neighborhood.get());
            limpaTodosOsCaches();
            return ResponseEntity.ok().body("");
        }

        return ResponseEntity.notFound().build();
    }

}
