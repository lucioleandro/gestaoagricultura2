package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadePecuaria;
import br.com.smart4.gestaoagriculturaapi.api.service.AtividadePecuariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
@RequestMapping("/atividadepecuaria")
public class AtividadePecuariaController {

	@Autowired
	private AtividadePecuariaService atividadePecuariaService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraAtividadePecuaria(@RequestBody AtividadePecuaria request) {
		try {
			return ResponseEntity.created(null).body(atividadePecuariaService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaAtividadePecuaria(@RequestBody AtividadePecuaria request) {
		try {
			return ResponseEntity.ok().body(atividadePecuariaService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<AtividadePecuaria> getListaAtividadePecuaria() {
		try {
			return atividadePecuariaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabypropriedade")
	public List<AtividadePecuaria> getListaAtividadePecuariaByPropriedade(@Param(value = "id") Long id) {
		try {
			return atividadePecuariaService.findByPropriedadeId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeAtividadePecuaria(@PathVariable Long id) {
		try {
			Optional<AtividadePecuaria> atividadePecuaria = atividadePecuariaService.findById(id);

			if (atividadePecuaria.isPresent()) {
				atividadePecuariaService.remove(atividadePecuaria.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}

