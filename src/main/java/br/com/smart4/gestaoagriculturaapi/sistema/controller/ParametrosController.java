package br.com.smart4.gestaoagriculturaapi.sistema.controller;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Parametros;
import br.com.smart4.gestaoagriculturaapi.sistema.service.ParametrosService;
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
public class ParametrosController  {
	
	@Autowired
	private ParametrosService parametrosService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraParametros(@Valid @RequestBody Parametros request) {
		try {
			return ResponseEntity.created(null).body(parametrosService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaParametros(@RequestBody Parametros request) {
		try {
			return ResponseEntity.ok().body(parametrosService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<Parametros> getListaParametroses() {
		try {
			return parametrosService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeParametros(@PathVariable Long id) {
		try {
			Optional<Parametros> parametros = parametrosService.findById(id);

			if (parametros.isPresent()) {
				parametrosService.remove(parametros.get());
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
