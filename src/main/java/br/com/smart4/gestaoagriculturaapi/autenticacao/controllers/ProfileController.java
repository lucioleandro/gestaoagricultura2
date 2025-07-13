package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.ProfileService;
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
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraPerfil(@RequestBody Profile request) {
        return ResponseEntity.created(null).body(profileService.create(request));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaPerfil(@RequestBody Profile request) {
        return ResponseEntity.ok().body(profileService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<Profile> getListaPerfil() {
        return profileService.findAll();
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removePerfil(@PathVariable Long id) {
        Optional<Profile> perfil = profileService.findById(id);

        if (perfil.isPresent()) {
            profileService.remove(perfil.get());
            return ResponseEntity.ok().body("");
        } else if (!perfil.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
