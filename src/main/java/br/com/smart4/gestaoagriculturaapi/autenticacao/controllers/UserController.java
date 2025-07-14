package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.smart4.gestaoagriculturaapi.api.utils.ResponseMessage;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints for managing system users")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Registers a new user in the system")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request) {
        new CPFValidator().assertValid(request.getCpf());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserResponse created = userService.create(request);
        return ResponseEntity.created(URI.create("/users/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user", description = "Updates a user by ID")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserRequest request) {
        new CPFValidator().assertValid(request.getCpf());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return ResponseEntity.ok(userService.update(id, request));
    }

    @PatchMapping
    @Operation(summary = "Update basic user data", description = "Updates user's name, login, and email")
    public ResponseEntity<UserResponse> updateBasicData(
            @RequestParam String nome,
            @RequestParam String login,
            @RequestParam String novologin,
            @RequestParam String email
    ) {
        UserResponse updated = userService.updateBasicData(login, nome, novologin, email);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    @Operation(summary = "List all users", description = "Returns a list of all users")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Deletes a user by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/pass")
    @Operation(
            summary = "Update user password",
            description = "Changes the user's password if the current password matches"
    )
    public ResponseEntity<UserResponse> updatePassword(
            @RequestParam String senhaAtual,
            @RequestParam String novaSenha,
            @RequestParam String login
    ) {
        UserResponse resp = userService.changePassword(login, senhaAtual, novaSenha);
        return ResponseEntity.ok(resp);
    }

}
