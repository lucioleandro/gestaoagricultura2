package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.smart4.gestaoagriculturaapi.api.utils.ResponseMessage;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request) {
        new CPFValidator().assertValid(request.getCpf());
//        request.setPassword(passwordEncoder.encode(request.getPassword())); //TODO Resolver
        UserResponse created = userService.create(request);
        return ResponseEntity.created(URI.create("/users/" + created.getId())).body(created);
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest request) {
        new CPFValidator().assertValid(request.getCpf());
//        request.setPassword(passwordEncoder.encode(request.getPassword())); TODO Resolver
        return ResponseEntity.ok(userService.update(request));
    }

    @PatchMapping
    public ResponseEntity<UserResponse> updateBasicData(@RequestParam String nome,
                                                        @RequestParam String login,
                                                        @RequestParam String novologin,
                                                        @RequestParam String email) {
//        return userService.findByLogin(login)
//                .map(user -> {
//                    user.setNome(nome);
//                    user.setLogin(novologin);
//                    user.setEmail(email);
//                    return ResponseEntity.ok(userService.update(user));
//                })
//                .orElseGet(() -> ResponseEntity.badRequest().build()); // TODO resolver
        return null;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
//        return userService.findById(id)
//                .map(user -> {
//                    userService.remove(user);
//                    return ResponseEntity.ok().build();
//                })
//                .orElseGet(() ->
//                        ResponseEntity.badRequest().body("Não existe esse registro no banco de dados")
//                );
        //todo levar para o service
        return null;
    }

    @PatchMapping("/pass")
    public ResponseEntity<?> updatePassword(@RequestParam String senhaAtual,
                                            @RequestParam String novaSenha,
                                            @RequestParam String login) {

        Optional<UserResponse> optionalUser = userService.findByLogin(login);

//        if (optionalUser.isPresent()) {
//            var userResponse = optionalUser.get();
//            if (passwordEncoder.matches(senhaAtual, userResponse.getPassword())) {
//                userResponse.setPassword(passwordEncoder.encode(novaSenha));
//                return ResponseEntity.ok(userService.update(userResponse));
//            }
//            return ResponseEntity.badRequest().body(new ResponseMessage("A senha atual informada está incorreta"));
//        }
        //todo levar para o service

        return ResponseEntity.badRequest().body(new ResponseMessage("Usuário não encontrado"));
    }
}
