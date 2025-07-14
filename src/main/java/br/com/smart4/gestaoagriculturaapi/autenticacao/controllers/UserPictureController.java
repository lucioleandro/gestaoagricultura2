package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserPictureRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserPictureResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserPictureService;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<UserPictureResponse> create(@RequestBody @Valid UserPictureRequest request) {
        UserPictureResponse created = userPictureService.create(request);
        return ResponseEntity.created(URI.create("/user-pictures/" + created.getId())).body(created);
    }

    @PatchMapping
    public ResponseEntity<UserPictureResponse> updateProfilePicture(@RequestParam("fotoPerfil") String fotoPerfil,
                                                                    @RequestParam("login") String login) {

//        Optional<UserResponse> userOpt = userService.findByLogin(login);
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        User user = userOpt.get();
//        UserPicture userPicture = userPictureService.findByUsuarioLogin(login)
//                .orElse(new UserPicture());
//
//        userPicture.setFotoPerfil(fotoPerfil);
//        userPicture.setUsuario(user);
        //TODO levar logica para o service

        return ResponseEntity.ok(userPictureService.update(null)); //TODO substituir aqui
    }

    @GetMapping
    public ResponseEntity<List<UserPictureResponse>> findAll() {
        return ResponseEntity.ok(userPictureService.findAll());
    }

    @GetMapping("/usuario")
    public ResponseEntity<UserPictureResponse> getByLogin(@Param(value = "id") String login) {
        return ResponseEntity.ok().body(userPictureService.findByUsuarioLogin(login));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        //todo levar para o service
//        return userPictureService.findById(id)
//                .map(userPicture -> {
//                    userPictureService.remove(userPicture);
//                    return ResponseEntity.ok().build();
//                })
//                .orElseGet(() ->
//                        ResponseEntity.badRequest().body("Não existe esse registro no banco de dados"));
        return null;
    }
}
