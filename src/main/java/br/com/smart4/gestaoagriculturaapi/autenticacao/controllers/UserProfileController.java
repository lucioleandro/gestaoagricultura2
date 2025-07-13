package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserProfileService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<?> cadastraUsuarioPerfil(@RequestBody UserProfile request) {
        return ResponseEntity.created(null).body(userProfileService.create(request));
    }

    @GetMapping
    public List<UserProfile> getListaUsuarioPerfil() {
        return userProfileService.findAll();
    }

    @GetMapping("/user")
    public List<UserProfile> getListaUsuarioPerfiByUsuario(@Param(value = "id") Long userId) {
        return userProfileService.findByUsuarioId(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUsuarioPerfil(@PathVariable Long id) {
        Optional<UserProfile> usuarioPerfil = userProfileService.findById(id);

        if (usuarioPerfil.isPresent()) {
            userProfileService.remove(usuarioPerfil.get());
            return ResponseEntity.ok().body("");
        } else if (!usuarioPerfil.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<?> atualizaUsuarioPerfil(@RequestBody UserProfile request) {
        return ResponseEntity.ok().body(userProfileService.atualiza(request));
    }

}
