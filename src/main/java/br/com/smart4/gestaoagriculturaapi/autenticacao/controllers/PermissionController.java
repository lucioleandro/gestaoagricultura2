package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.PermissionRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.PermissionResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.PermissionService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> create(@RequestBody @Valid PermissionRequest request) {
        PermissionResponse created = permissionService.create(request);
        return ResponseEntity.created(URI.create("/permissions/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponse> update(@PathVariable Long id, @RequestBody @Valid PermissionRequest request) {
        return ResponseEntity.ok(permissionService.update(id, request));
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> getAll() {
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping("/profile")
    public ResponseEntity<List<PermissionResponse>> getByProfile(@Param("id") Long profileId) {
        return ResponseEntity.ok(permissionService.findByPerfilId(profileId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        //TODO levar para o service
//        return permissionService.findById(id)
//                .map(p -> {
//                    permissionService.remove(p);
//                    return ResponseEntity.ok().build();
//                })
//                .orElseGet(() -> ResponseEntity.badRequest().body("Não existe esse registro no banco de dados"));
        return null;
    }
}
