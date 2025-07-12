package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.smart4.gestaoagriculturaapi.api.domain.Agricultor;
import br.com.smart4.gestaoagriculturaapi.api.service.AgricultorService;
import br.com.smart4.gestaoagriculturaapi.api.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agricultor")
public class AgricultorController {

	@Autowired
	private AgricultorService agricultorService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraAgricultor(@RequestBody Agricultor request,
												UriComponentsBuilder uriBuilder) throws InvalidStateException {
		try {
			new CPFValidator().assertValid(request.getCpf());
			
			URI uri = uriBuilder.path("/agricultor/getbycpf?cpf={cpf}")
					.buildAndExpand(request.getCpf()).toUri();
			
			return ResponseEntity.created(uri).body(agricultorService.create(request));
			
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage("O CPF Informado já está cadastrado na base"));
			
		} catch (InvalidStateException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage("O CPF Informado é inválido"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaAgricultor(@RequestBody Agricultor request) {
		try {
			new CPFValidator().assertValid(request.getCpf());
			
			return ResponseEntity.ok().body(agricultorService.atualiza(request));
			
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage("O CPF Informado já está cadastrado na base"));
			
		} catch (InvalidStateException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage("O CPF Informado é inválido"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/lista")
	public List<Agricultor> getListaAgricultores() {
		try {
			return agricultorService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/getbycpf")
	public Agricultor getAgricultorByCpf(@Param(value = "cpf") String cpf) {
		try {
			Optional<Agricultor> agricultor = agricultorService.findByCpf(cpf);
			
			if(agricultor.isPresent()) {
				return agricultor.get();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeAgricultor(@PathVariable Long id) {
		try {
			Optional<Agricultor> agricultor = agricultorService.findById(id);

			if (agricultor.isPresent()) {
				agricultorService.remove(agricultor.get());
				return ResponseEntity.ok().body("");
			} 
			
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
