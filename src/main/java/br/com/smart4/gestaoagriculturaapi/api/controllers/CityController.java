package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.CityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.CityResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Cities", description = "Endpoints for managing cities")
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService municipioService;

    public CityController(CityService municipioService) {
        this.municipioService = municipioService;
    }

    @Operation(summary = "Create a new city", description = "Registers a new city in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "City created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    @CacheEvict(value = "listaDeMunicipios", allEntries = true)
    public ResponseEntity<CityResponse> create(@RequestBody @Valid CityRequest request) {
        return ResponseEntity.created(null).body(municipioService.create(request));
    }

    @Operation(summary = "Update a city", description = "Updates the city with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "City updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeMunicipios", allEntries = true)
    public ResponseEntity<CityResponse> update(@PathVariable Long id, @RequestBody @Valid CityRequest request) {
        return ResponseEntity.ok().body(municipioService.update(id, request));
    }

    @Operation(summary = "Get all cities", description = "Returns a list of all registered cities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    })
    @GetMapping
    @Cacheable(value = "listaDeMunicipios")
    public ResponseEntity<List<CityResponse>> getList() {
        return ResponseEntity.ok(municipioService.findAll());
    }

    @Operation(summary = "Delete a city", description = "Deletes the city with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "City deleted successfully"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
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
