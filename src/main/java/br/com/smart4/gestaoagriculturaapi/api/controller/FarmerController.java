package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.smart4.gestaoagriculturaapi.api.domain.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.service.FarmerService;
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
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	private FarmerService farmerService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraFarmer(@RequestBody Farmer request,
												UriComponentsBuilder uriBuilder) throws InvalidStateException {
		try {
			new CPFValidator().assertValid(request.getCpf());
			
			URI uri = uriBuilder.path("/farmer/getbycpf?cpf={cpf}")
					.buildAndExpand(request.getCpf()).toUri();
			
			return ResponseEntity.created(uri).body(farmerService.create(request));
			
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
	public ResponseEntity<?> atualizaFarmer(@RequestBody Farmer request) {
		try {
			new CPFValidator().assertValid(request.getCpf());
			
			return ResponseEntity.ok().body(farmerService.atualiza(request));
			
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
	public List<Farmer> getListaFarmeres() {
		try {
			return farmerService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/getbycpf")
	public Farmer getFarmerByCpf(@Param(value = "cpf") String cpf) {
		try {
			Optional<Farmer> farmer = farmerService.findByCpf(cpf);
			
			if(farmer.isPresent()) {
				return farmer.get();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeFarmer(@PathVariable Long id) {
		try {
			Optional<Farmer> farmer = farmerService.findById(id);

			if (farmer.isPresent()) {
				farmerService.remove(farmer.get());
				return ResponseEntity.ok().body("");
			} 
			
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
