package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AddressResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

@Tag(name = "Address", description = "Endpoints for managing address data")
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Create a new address", description = "Registers a new address based on the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody @Valid AddressRequest request) {
        return ResponseEntity.created(null).body(addressService.create(request));
    }

    @Operation(summary = "Update an existing address", description = "Updates the address details for a given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Long id, @RequestBody @Valid AddressRequest request) {
        return ResponseEntity.ok().body(addressService.update(id, request));
    }

    @Operation(summary = "Get all addresses", description = "Retrieves a list of all registered addresses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<AddressResponse>> getList() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @Operation(summary = "Remove an address", description = "Deletes the address with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAddress(@PathVariable Long id) {
        addressService.remove(id);
        return ResponseEntity.notFound().build();
    }

}
