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
//        request.setPassword(passwordEncoder.encode(request.getPassword())); //TODO Resolver
        UserResponse created = userService.create(request);
        return ResponseEntity.created(URI.create("/users/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user", description = "Updates a user by ID")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserRequest request) {
        new CPFValidator().assertValid(request.getCpf());
//        request.setPassword(passwordEncoder.encode(request.getPassword())); TODO Resolver
        return ResponseEntity.ok(userService.update(id, request));
    }

    @PatchMapping
    @Operation(summary = "Update basic user data", description = "Updates user's name, login, and email")
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
    @Operation(summary = "List all users", description = "Returns a list of all users")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Deletes a user by ID")
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
    @Operation(summary = "Update user password", description = "Changes the user's password if the current password matches")
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
