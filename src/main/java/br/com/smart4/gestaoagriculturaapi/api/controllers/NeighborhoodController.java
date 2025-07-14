package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.NeighborhoodRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.NeighborhoodResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.NeighborhoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Neighborhood", description = "Endpoints for managing neighborhoods")
@RestController
@RequestMapping("/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;
    private final CacheManager cacheManager;

    public NeighborhoodController(NeighborhoodService neighborhoodService, CacheManager cacheManager) {
        this.neighborhoodService = neighborhoodService;
        this.cacheManager = cacheManager;
    }

    @Operation(summary = "Create a neighborhood", description = "Registers a new neighborhood in the system and clears all neighborhood-related caches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Neighborhood created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<NeighborhoodResponse> create(@RequestBody @Valid NeighborhoodRequest request) {
        limpaTodosOsCaches();
        NeighborhoodResponse response = neighborhoodService.create(request);
        return ResponseEntity.created(null).body(response);
    }

    @Operation(summary = "Update a neighborhood", description = "Updates the neighborhood with the given ID and clears related caches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Neighborhood updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Neighborhood not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<NeighborhoodResponse> update(@PathVariable Long id, @RequestBody @Valid NeighborhoodRequest request) {
        limpaTodosOsCaches();
        NeighborhoodResponse response = neighborhoodService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "List all neighborhoods", description = "Retrieves all neighborhoods from cache or service")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    @Cacheable(value = "listaDeNeighborhoods")
    public ResponseEntity<List<NeighborhoodResponse>> getList() {
        List<NeighborhoodResponse> responses = neighborhoodService.findAll();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "List neighborhoods by city", description = "Retrieves neighborhoods that belong to a specific city name")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping("/city")
    @Cacheable(value = "listaDeNeighborhoodsPorMunicipio")
    public ResponseEntity<List<NeighborhoodResponse>> getListByCity(@Param("city") String municipio) {
        List<NeighborhoodResponse> responses = neighborhoodService.findByMunicipioNome(municipio);
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Delete a neighborhood", description = "Deletes a neighborhood by ID. Cache will be cleared if deletion is implemented")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Neighborhood deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Neighborhood not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        neighborhoodService.remove(id);
        return ResponseEntity.notFound().build();
    }

    private void limpaTodosOsCaches() {
        cacheManager.getCache("listaDeNeighborhoods").clear();
        cacheManager.getCache("listaDeNeighborhoodsPorMunicipio").clear();
    }
}
