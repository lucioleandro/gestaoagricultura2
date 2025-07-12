package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.DadosSociais;
import br.com.smart4.gestaoagriculturaapi.api.service.DadosSociaisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dadossociais")
public class DadosSociaisController {

	@Autowired
	private DadosSociaisService dadosSociaisService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraDadosSociais(@RequestBody DadosSociais request) {
		try {
			return ResponseEntity.created(null).body(dadosSociaisService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("/atualiza")
	public ResponseEntity<?> atualizaDadosSociais(@RequestBody DadosSociais request) {
		try {
			return ResponseEntity.ok().body(dadosSociaisService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/lista")
	public List<DadosSociais> getListaDadosSociaises() {
		try {
			return dadosSociaisService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@PostMapping("/remove")
	public ResponseEntity<?> removeDadosSociais(@Valid @RequestBody Long id) {
		try {
			Optional<DadosSociais> dadosSociais = dadosSociaisService.findById(id);

			if (dadosSociais.isPresent()) {
				dadosSociaisService.remove(dadosSociais.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
