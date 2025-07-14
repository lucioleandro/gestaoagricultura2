package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.EconomicActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Economic Activities", description = "Endpoints for managing economic activities")
@RestController
@RequestMapping("/economic-activities")
public class EconomicActivityController {

    private final EconomicActivityService economicActivityService;

    public EconomicActivityController(EconomicActivityService economicActivityService) {
        this.economicActivityService = economicActivityService;
    }

    @Operation(summary = "Create a new economic activity", description = "Registers a new economic activity in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Economic activity created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    @CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
    public ResponseEntity<EconomicActivityResponse> create(@RequestBody @Valid EconomicActivityRequest request) {
        return ResponseEntity.created(null).body(economicActivityService.create(request));
    }

    @Operation(summary = "Update an economic activity", description = "Updates an existing economic activity by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Economic activity updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Economic activity not found")
    })
    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
    public ResponseEntity<EconomicActivityResponse> update(@PathVariable Long id, @RequestBody @Valid EconomicActivityRequest request) {
        return ResponseEntity.ok().body(economicActivityService.update(id, request));
    }

    @Operation(summary = "Get all economic activities", description = "Returns a list of all registered economic activities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    })
    @GetMapping
    @Cacheable(value = "listaDeAtividadesEconomicas")
    public ResponseEntity<List<EconomicActivityResponse>> getList() {
        return ResponseEntity.ok(economicActivityService.findAll());
    }

    @Operation(summary = "Delete an economic activity", description = "Deletes an economic activity by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Economic activity deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Economic activity not found")
    })
    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeAtividadesEconomicas", allEntries = true)
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        economicActivityService.remove(id);
        return ResponseEntity.notFound().build();
    }

}
