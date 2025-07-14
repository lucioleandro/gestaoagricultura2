package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.ProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.ProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
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

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/profiles")
@Tag(name = "Profiles", description = "Endpoints for managing user profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new profile",
            description = "Registers a new user profile"
    )
    public ResponseEntity<ProfileResponse> create(@RequestBody @Valid ProfileRequest request) {
        ProfileResponse created = profileService.create(request);
        return ResponseEntity.created(URI.create("/profiles/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing profile",
            description = "Updates the user profile with the specified ID"
    )
    public ResponseEntity<ProfileResponse> update(@PathVariable Long id, @RequestBody @Valid ProfileRequest request) {
        return ResponseEntity.ok(profileService.update(id, request));
    }

    @GetMapping
    @Operation(
            summary = "List all profiles",
            description = "Retrieves all user profiles"
    )
    public ResponseEntity<List<ProfileResponse>> getAll() {
        return ResponseEntity.ok(profileService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a profile",
            description = "Deletes a user profile by ID"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        profileService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
