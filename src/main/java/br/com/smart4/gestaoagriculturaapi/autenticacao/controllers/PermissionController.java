package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.PermissionRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.PermissionResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
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

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/permissions")
@Tag(name = "Permissions", description = "Endpoints for managing access permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new permission",
            description = "Registers a new permission in the system"
    )
    public ResponseEntity<PermissionResponse> create(@RequestBody @Valid PermissionRequest request) {
        PermissionResponse created = permissionService.create(request);
        return ResponseEntity.created(URI.create("/permissions/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing permission",
            description = "Updates a permission with the provided ID"
    )
    public ResponseEntity<PermissionResponse> update(@PathVariable Long id, @RequestBody @Valid PermissionRequest request) {
        return ResponseEntity.ok(permissionService.update(id, request));
    }

    @GetMapping
    @Operation(
            summary = "List all permissions",
            description = "Returns a list of all permissions in the system"
    )
    public ResponseEntity<List<PermissionResponse>> getAll() {
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping("/profile")
    @Operation(
            summary = "Get permissions by profile ID",
            description = "Returns a list of permissions associated with a specific profile"
    )
    public ResponseEntity<List<PermissionResponse>> getByProfile(@Param("id") Long profileId) {
        return ResponseEntity.ok(permissionService.findByPerfilId(profileId));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a permission",
            description = "Deletes the permission with the given ID"
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
