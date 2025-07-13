package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.smart4.gestaoagriculturaapi.api.utils.ResponseMessage;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> cadastraUsuario(@RequestBody @Valid UserRequest request) {
        new CPFValidator().assertValid(request.getCpf());

//        String senhaCriptografada = new BCryptPasswordEncoder().encode(request.getPassword());
//        request.setSenha(senhaCriptografada);
        // TODO Resolver acima

        return ResponseEntity.created(null).body(userService.create(request));
    }

    @PutMapping
    public ResponseEntity<?> atualizaUsuario(@RequestBody @Valid UserRequest request) {
        new CPFValidator().assertValid(request.getCpf());

//        String senhaCriptografada = new BCryptPasswordEncoder().encode(request.getPassword());
//        request.setSenha(senhaCriptografada);
        // TODO Resolver acima

        return ResponseEntity.ok().body(userService.atualiza(request));
    }

    @PatchMapping
    public ResponseEntity<?> atualizaUsuario(@RequestParam("nome") String nome,
                                             @RequestParam("login") String login,
                                             @RequestParam("novologin") String novoLogin,
                                             @RequestParam("email") String email) {
        Optional<User> optionalUsuario = this.userService.findByLogin(login);

        User usuario = null;

        if (optionalUsuario.isPresent()) {
            usuario = optionalUsuario.get();
            usuario.setLogin(novoLogin);
            usuario.setNome(nome);
            usuario.setEmail(email);
        }

        return ResponseEntity.ok()
                .body(userService.atualiza(usuario));
    }

    @GetMapping
    public List<User> getListaUsuarioes() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUsuario(@PathVariable Long id) {
        Optional<User> usuario = userService.findById(id);

        if (usuario.isPresent()) {
            userService.remove(usuario.get());
            return ResponseEntity.ok().body("");
        } else if (!usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/pass")
    public ResponseEntity<?> atualizaSenhaUsuario(@RequestParam("senhaAtual") String senhaAtual,
                                                  @RequestParam("novaSenha") String novaSenha,
                                                  @RequestParam("login") String login) {

        Optional<User> optionalUsuario = userService.findByLogin(login);
        User usuario = null;

        if (optionalUsuario.isPresent()) {
            usuario = optionalUsuario.get();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            String novaSenhaCriptografada = encoder.encode(novaSenha);

            boolean senhaAtualEhValida = encoder.matches(senhaAtual, usuario.getSenha());

            if (senhaAtualEhValida) {
                usuario.setSenha(novaSenhaCriptografada);

                return ResponseEntity.ok().body(userService.atualiza(usuario));
            }

            return ResponseEntity.badRequest().body(new ResponseMessage("A senha atual informada está incorreta"));

        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("Houve um erro inesperado, por favor, entre em contato com o suporte"));
        }
    }

}
