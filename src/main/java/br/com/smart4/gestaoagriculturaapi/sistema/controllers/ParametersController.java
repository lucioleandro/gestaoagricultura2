package br.com.smart4.gestaoagriculturaapi.sistema.controllers;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Parameters;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.ParametersResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.services.ParametersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Parameters", description = "Endpoints for managing system parameters")
@RestController
@RequestMapping("/parameters")
public class ParametersController {

    private final ParametersService parametersService;

    public ParametersController(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    @Operation(summary = "Create system parameters", description = "Creates a new set of system parameters")
    @PostMapping
    public ResponseEntity<ParametersResponse> create(@Valid @RequestBody Parameters request) {
        return ResponseEntity.created(null).body(parametersService.create(request));
    }

    @Operation(summary = "Update system parameters", description = "Updates existing system parameters")
    @PutMapping
    public ResponseEntity<ParametersResponse> update(@RequestBody Parameters request) {
        return ResponseEntity.ok().body(parametersService.update(request));
    }

    @Operation(summary = "List all system parameters", description = "Returns all registered system parameters")
    @GetMapping
    public ResponseEntity<List<ParametersResponse>> getList() {
        return ResponseEntity.ok(parametersService.findAll());
    }

    @Operation(summary = "Delete system parameters by ID", description = "Removes a specific set of system parameters by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        parametersService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
