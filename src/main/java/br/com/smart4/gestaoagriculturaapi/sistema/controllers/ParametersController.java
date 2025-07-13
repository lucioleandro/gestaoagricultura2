package br.com.smart4.gestaoagriculturaapi.sistema.controllers;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Parameters;
import br.com.smart4.gestaoagriculturaapi.sistema.services.ParametersService;
import jakarta.validation.Valid;
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
@RequestMapping("/parametros")
public class ParametersController {

    private final ParametersService parametersService;

    public ParametersController(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraParametros(@Valid @RequestBody Parameters request) {
        return ResponseEntity.created(null).body(parametersService.create(request));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaParametros(@RequestBody Parameters request) {
        return ResponseEntity.ok().body(parametersService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<Parameters> getListaParametroses() {
        return parametersService.findAll();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeParametros(@PathVariable Long id) {
        Optional<Parameters> parametros = parametersService.findById(id);

        if (parametros.isPresent()) {
            parametersService.remove(parametros.get());
            return ResponseEntity.ok().body("");
        } else if (!parametros.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
