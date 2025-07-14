package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AgricultureActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AgricultureActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.AgricultureActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

@Tag(name = "Agriculture Activities", description = "Endpoints for managing agriculture activities")
@RestController
@RequestMapping("/agriculture-activities")
public class AgricultureActivityController {

    private final AgricultureActivityService agricultureActivityService;

    public AgricultureActivityController(AgricultureActivityService agricultureActivityService) {
        this.agricultureActivityService = agricultureActivityService;
    }

    @Operation(summary = "Create a new agriculture activity", description = "Registers a new agriculture activity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<AgricultureActivityResponse> create(@RequestBody @Valid AgricultureActivityRequest request) {
        return ResponseEntity.created(null).body(agricultureActivityService.create(request));
    }

    @Operation(summary = "Update an agriculture activity", description = "Updates the activity with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AgricultureActivityResponse> update(@PathVariable Long id, @RequestBody @Valid AgricultureActivityRequest request) {
        return ResponseEntity.ok().body(agricultureActivityService.update(id, request));
    }

    @Operation(summary = "Get all agriculture activities", description = "Returns a list of all registered agriculture activities.")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    public ResponseEntity<List<AgricultureActivityResponse>> getList() {
        return ResponseEntity.ok(agricultureActivityService.findAll());
    }

    @Operation(summary = "Get agriculture activities by property", description = "Returns a list of activities associated with a specific property ID.")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping("/property")
    public ResponseEntity<List<AgricultureActivityResponse>> getListByProperty(@Param(value = "id") Long id) {
        return ResponseEntity.ok(agricultureActivityService.findByProperty(id));
    }

    @Operation(summary = "Delete an agriculture activity", description = "Deletes the activity with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Activity deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        agricultureActivityService.remove(id);
        return ResponseEntity.notFound().build();
    }

}
