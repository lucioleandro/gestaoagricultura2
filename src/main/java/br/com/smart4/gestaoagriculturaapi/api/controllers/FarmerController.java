package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.FarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.FarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.FarmerService;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Farmer", description = "Endpoints for managing farmers")
@RestController
@RequestMapping("/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @Operation(summary = "Create a farmer", description = "Registers a new farmer in the system after validating the CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Farmer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<FarmerResponse> create(@RequestBody @Valid FarmerRequest request,
                                                 UriComponentsBuilder uriBuilder) throws InvalidStateException {
        new CPFValidator().assertValid(request.getCpf());

        URI uri = uriBuilder.path("/farmers/document?cpf={cpf}")
                .buildAndExpand(request.getCpf()).toUri();

        return ResponseEntity.created(uri).body(farmerService.create(request));
    }

    @Operation(summary = "Update a farmer", description = "Updates farmer data based on the provided ID after CPF validation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Farmer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid CPF or request data"),
            @ApiResponse(responseCode = "404", description = "Farmer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FarmerResponse> update(@PathVariable Long id, @RequestBody @Valid FarmerRequest request) {
        new CPFValidator().assertValid(request.getCpf());

        return ResponseEntity.ok().body(farmerService.update(id, request));
    }

    @Operation(summary = "List all farmers", description = "Retrieves a list of all registered farmers")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    public ResponseEntity<List<FarmerResponse>> getList() {
        return ResponseEntity.ok(farmerService.findAll());
    }

    @Operation(summary = "Get farmer by CPF", description = "Retrieves a farmer by their CPF document")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Farmer found"),
            @ApiResponse(responseCode = "404", description = "Farmer not found")
    })
    @GetMapping("/document")
    public ResponseEntity<FarmerResponse> getByCpf(@Param(value = "cpf") String cpf) {
//        Optional<Farmer> farmer = farmerService.findByCpf(cpf);
//
//        if (farmer.isPresent()) {
//            return farmer.get();
//        }

        // TODO levar lógica para o service

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a farmer", description = "Deletes a farmer from the system based on the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Farmer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        Optional<Farmer> farmer = farmerService.findById(id);
//
//        if (farmer.isPresent()) {
//            farmerService.remove(farmer.get());
//            return ResponseEntity.ok().body("");
//        }

        // TODO levar lógica service
        return ResponseEntity.notFound().build();
    }

}
