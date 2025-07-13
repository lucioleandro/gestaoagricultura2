package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserPictureRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserPictureService;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user-pictures")
public class UserPictureController {

    private final UserPictureService userPictureService;

    private final UserService userService;

    public UserPictureController(UserPictureService userPictureService, UserService userService) {
        this.userPictureService = userPictureService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> cadastraUsuarioFoto(@RequestBody @Valid UserPictureRequest request) {
        return ResponseEntity.created(null).body(userPictureService.create(request));
    }

    @PatchMapping
    public ResponseEntity<?> atualizaUsuarioFoto(@RequestParam("fotoPerfil") String fotoPerfil,
                                                 @RequestParam("login") String login) {

        Optional<UserPicture> usuarioFotoOptional = userPictureService.findByUsuarioLogin(login);
        Optional<User> usuarioOptional = userService.findByLogin(login);

        UserPicture userPicture = null;

        if (usuarioOptional.isPresent() && usuarioFotoOptional.isPresent()) {
            userPicture = usuarioFotoOptional.get();

            userPicture.setFotoPerfil(fotoPerfil);
            userPicture.setUsuario(usuarioOptional.get());

        } else if (!usuarioFotoOptional.isPresent() && usuarioOptional.isPresent()) {
            userPicture = new UserPicture(fotoPerfil, usuarioOptional.get());
        }

        return ResponseEntity.ok().body(userPictureService.createOrAtualiza(userPicture));
    }

    @GetMapping
    public List<UserPicture> getListaUsuarioFoto() {
        return userPictureService.findAll();
    }

    @GetMapping("usuario")
    public UserPicture getFotoByUsuario(@Param(value = "id") String login) {
        Optional<UserPicture> usuarioOptional = userPictureService.findByUsuarioLogin(login);

        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUsuarioFoto(@PathVariable Long id) {
        Optional<UserPicture> usuarioFoto = userPictureService.findById(id);

        if (usuarioFoto.isPresent()) {
            userPictureService.remove(usuarioFoto.get());
            return ResponseEntity.ok().body("");
        } else if (!usuarioFoto.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
