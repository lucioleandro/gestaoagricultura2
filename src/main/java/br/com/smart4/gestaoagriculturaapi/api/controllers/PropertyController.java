package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PropertyRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PropertyResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.AddressService;
import br.com.smart4.gestaoagriculturaapi.api.services.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Tag(name = "Property", description = "Endpoints for managing agricultural properties")
@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final AddressService addressService;

    public PropertyController(PropertyService propertyService, AddressService addressService) {
        this.propertyService = propertyService;
        this.addressService = addressService;
    }

    @Operation(summary = "Create a property", description = "Registers a new property and calculates coordinates based on the provided address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Property created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid property or address data")
    })
    @PostMapping
    public ResponseEntity<PropertyResponse> create(
            @RequestBody @Valid PropertyRequest request) throws IOException {
        PropertyResponse response = propertyService.create(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Update a property", description = "Updates property and its coordinates based on the address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> update(@PathVariable Long id, @RequestBody @Valid PropertyRequest request)
            throws IOException {
        PropertyResponse resp = propertyService.update(id, request);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "List all properties", description = "Returns a list of all registered properties")
    @ApiResponse(responseCode = "200", description = "Properties retrieved successfully")
    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getList() {
        return ResponseEntity.ok(propertyService.findAll());
    }

    @Operation(summary = "List properties by farmer ID", description = "Returns properties associated with a specific farmer")
    @ApiResponse(responseCode = "200", description = "Properties retrieved successfully")
    @GetMapping("/farmer")
    public ResponseEntity<List<PropertyResponse>> getListByFarmer(
            @Parameter(description = "Farmer ID") @Param(value = "id") Long farmerId) {
        return ResponseEntity.ok(propertyService.findByFarmer(farmerId));
    }

    @Operation(summary = "Delete a property", description = "Deletes a property and its associated address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        propertyService.remove(id);
        return ResponseEntity.ok().build();
    }

}
