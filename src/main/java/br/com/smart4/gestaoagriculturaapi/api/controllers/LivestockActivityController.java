package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.LivestockActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.LivestockActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.LivestockActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Livestock Activity", description = "Endpoints for managing livestock activities")
@RestController
@RequestMapping("/livestock-activities")
public class LivestockActivityController {

    private final LivestockActivityService livestockActivityService;

    public LivestockActivityController(LivestockActivityService livestockActivityService) {
        this.livestockActivityService = livestockActivityService;
    }

    @Operation(summary = "Create a livestock activity", description = "Registers a new livestock activity in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livestock activity created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<LivestockActivityResponse> create(
            @RequestBody @Valid LivestockActivityRequest request) {

        LivestockActivityResponse created = livestockActivityService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }


    @Operation(summary = "Update a livestock activity", description = "Updates an existing livestock activity based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livestock activity updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Livestock activity not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LivestockActivityResponse> update(@PathVariable Long id, @RequestBody @Valid LivestockActivityRequest request) {
        return ResponseEntity.ok().body(livestockActivityService.update(id, request));
    }

    @Operation(summary = "List all livestock activities", description = "Retrieves a list of all livestock activities")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    public ResponseEntity<List<LivestockActivityResponse>> getList() {
        return ResponseEntity.ok(livestockActivityService.findAll());
    }

    @Operation(summary = "List livestock activities by property", description = "Retrieves livestock activities related to a specific property ID")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping("/property")
    public ResponseEntity<List<LivestockActivityResponse>> getListByProperty(@RequestParam("id") Long propertyId) {
        List<LivestockActivityResponse> responses = livestockActivityService.findByPropertyId(propertyId);
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Delete a livestock activity", description = "Deletes a livestock activity based on the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Livestock activity not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        livestockActivityService.remove(id);
        return ResponseEntity.notFound().build();
    }
}
