package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.ProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.ProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> create(@RequestBody @Valid ProfileRequest request) {
        ProfileResponse created = profileService.create(request);
        return ResponseEntity.created(URI.create("/profiles/" + created.getId())).body(created);
    }

    @PutMapping
    public ResponseEntity<ProfileResponse> update(@RequestBody @Valid ProfileRequest request) {
        return ResponseEntity.ok(profileService.update(request));
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getAll() {
        return ResponseEntity.ok(profileService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
//        Optional<ProfileResponse> perfil = profileService.findResponseById(id);
//
//        return perfil.map(p -> {
//            profileService.removeById(id);
//            return ResponseEntity.ok().build();
//        }).orElseGet(() ->
//                ResponseEntity.badRequest().body("Não existe esse registro no banco de dados")
//        );
        //todo levar para o service
        return null;
    }
}
