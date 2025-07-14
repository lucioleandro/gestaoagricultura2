package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserPictureRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserPictureResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserPictureService;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user-pictures")
@Tag(name = "User Pictures", description = "Endpoints for managing user profile pictures")
public class UserPictureController {

    private final UserPictureService userPictureService;
    private final UserService userService;

    public UserPictureController(UserPictureService userPictureService, UserService userService) {
        this.userPictureService = userPictureService;
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create a user picture", description = "Registers a new profile picture for a user")
    public ResponseEntity<UserPictureResponse> create(@RequestBody @Valid UserPictureRequest request) {
        UserPictureResponse created = userPictureService.create(request);
        return ResponseEntity.created(URI.create("/user-pictures/" + created.getId())).body(created);
    }

    @PatchMapping
    @Operation(
            summary = "Update a user's profile picture",
            description = "Updates a user's profile picture using login"
    )
    public ResponseEntity<UserPictureResponse> updateProfilePicture(
            @RequestParam("login")      String login,
            @RequestParam("fotoPerfil") String fotoPerfil
    ) {
        UserPictureResponse resp =
                userPictureService.updateProfilePicture(login, fotoPerfil);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    @Operation(summary = "List all user pictures", description = "Returns a list of all profile pictures")
    public ResponseEntity<List<UserPictureResponse>> findAll() {
        return ResponseEntity.ok(userPictureService.findAll());
    }

    @GetMapping("/usuario")
    @Operation(summary = "Get picture by user login", description = "Returns a user's profile picture based on login")
    public ResponseEntity<UserPictureResponse> getByLogin(@Param(value = "id") String login) {
        return ResponseEntity.ok().body(userPictureService.findByUsuarioLogin(login));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user picture", description = "Deletes a profile picture by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userPictureService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
