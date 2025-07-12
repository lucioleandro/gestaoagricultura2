package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.Endereco;
import br.com.smart4.gestaoagriculturaapi.api.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraEndereco(@RequestBody Endereco request) {
		try {
			return ResponseEntity.created(null).body(enderecoService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaEndereco(@RequestBody Endereco request) {
		try {
			return ResponseEntity.ok().body(enderecoService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<Endereco> getListaEnderecoes() {
		try {
			return enderecoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeEndereco(@PathVariable Long id) {
		try {
			Optional<Endereco> endereco = enderecoService.findById(id);

			if (endereco.isPresent()) {
				enderecoService.remove(endereco.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
