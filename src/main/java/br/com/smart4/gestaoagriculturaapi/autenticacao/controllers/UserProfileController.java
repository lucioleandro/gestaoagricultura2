package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user-profiles")
@Tag(name = "User Profiles", description = "Endpoints for managing associations between users and profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    @Operation(summary = "Create association", description = "Create a new association between a user and a profile")
    public ResponseEntity<UserProfileResponse> create(@RequestBody @Valid UserProfileRequest request) {
        UserProfileResponse created = userProfileService.create(request);
        return ResponseEntity.created(URI.create("/user-profiles/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update association", description = "Update an existing user-profile association by ID")
    public ResponseEntity<UserProfileResponse> update(@PathVariable Long id, @RequestBody @Valid UserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.update(id, request));
    }

    @GetMapping
    @Operation(summary = "List all associations", description = "Retrieve all user-profile associations")
    public ResponseEntity<List<UserProfileResponse>> findAll() {
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @GetMapping("/user")
    @Operation(summary = "List by user", description = "Retrieve profiles associated with a specific user ID")
    public ResponseEntity<List<UserProfileResponse>> findByUser(@Param(value = "id") Long userId) {
        return ResponseEntity.ok(userProfileService.findListByUserId(userId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete association", description = "Remove an association between a user and a profile by ID")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // TODO Levar lógica para o service
//        return userProfileService.findByUserId(id)
//                .map(up -> {
//                    userProfileService.remove(up);
//                    return ResponseEntity.ok().build();
//                })
//                .orElse(ResponseEntity.badRequest().body("Não existe esse registro no banco de dados"));
        return null;
    }
}
