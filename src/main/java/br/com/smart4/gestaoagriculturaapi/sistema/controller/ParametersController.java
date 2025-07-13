package br.com.smart4.gestaoagriculturaapi.sistema.controller;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Parameters;
import br.com.smart4.gestaoagriculturaapi.sistema.service.ParametersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parametros")
public class ParametersController {
	
	@Autowired
	private ParametersService parametersService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraParametros(@Valid @RequestBody Parameters request) {
		try {
			return ResponseEntity.created(null).body(parametersService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaParametros(@RequestBody Parameters request) {
		try {
			return ResponseEntity.ok().body(parametersService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<Parameters> getListaParametroses() {
		try {
			return parametersService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeParametros(@PathVariable Long id) {
		try {
			Optional<Parameters> parametros = parametersService.findById(id);

			if (parametros.isPresent()) {
				parametersService.remove(parametros.get());
				return ResponseEntity.ok().body("");
			} else if (!parametros.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
