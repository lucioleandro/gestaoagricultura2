package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<UserProfileResponse> create(@RequestBody @Valid UserProfileRequest request) {
        UserProfileResponse created = userProfileService.create(request);
        return ResponseEntity.created(URI.create("/user-profiles/" + created.getId())).body(created);
    }

    @PutMapping
    public ResponseEntity<UserProfileResponse> update(@RequestBody @Valid UserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.update(request));
    }

    @GetMapping
    public ResponseEntity<List<UserProfileResponse>> findAll() {
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserProfileResponse>> findByUser(@Param(value = "id") Long userId) {
        return ResponseEntity.ok(userProfileService.findListByUserId(userId));
    }

    @DeleteMapping("/{id}")
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
