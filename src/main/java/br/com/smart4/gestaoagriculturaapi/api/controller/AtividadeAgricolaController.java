package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadeAgricola;
import br.com.smart4.gestaoagriculturaapi.api.service.AtividadeAgricolaService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividadeagricola")
public class AtividadeAgricolaController {

	@Autowired
	private AtividadeAgricolaService atividadeAgricolaService;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraAtividadeAgricola(@RequestBody AtividadeAgricola request) {
		try {
			return ResponseEntity.created(null).body(atividadeAgricolaService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaAtividadeAgricola(@RequestBody AtividadeAgricola request) {
		try {
			atividadeAgricolaService.atualiza(request);

			return ResponseEntity.ok().body("");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<AtividadeAgricola> getListaAtividadeAgricola() {
		try {
			return atividadeAgricolaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/listabypropriedade")
	@ResponseBody
	public List<AtividadeAgricola> getListaAtividadeAgricolaByPropriedade(@Param(value = "id") Long id) {
		try {
			return atividadeAgricolaService.findByPropriedade(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeAtividadeAgricola(@PathVariable Long id) {
		try {
			Optional<AtividadeAgricola> atividadeAgricola = atividadeAgricolaService.findById(id);

			if (atividadeAgricola.isPresent()) {
				atividadeAgricolaService.remove(atividadeAgricola.get());
				return ResponseEntity.ok().body("");
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

}
