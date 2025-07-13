package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.service.PersonalInformationService;
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
public class PersonalInformationController {

	@Autowired
	private PersonalInformationService personalInformationService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraPersonalInformation(@RequestBody PersonalInformation request) {
		try {
			return ResponseEntity.created(null).body(personalInformationService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("/atualiza")
	public ResponseEntity<?> atualizaPersonalInformation(@RequestBody PersonalInformation request) {
		try {
			return ResponseEntity.ok().body(personalInformationService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/lista")
	public List<PersonalInformation> getListaPersonalInformationes() {
		try {
			return personalInformationService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@PostMapping("/remove")
	public ResponseEntity<?> removePersonalInformation(@Valid @RequestBody Long id) {
		try {
			Optional<PersonalInformation> personalInformation = personalInformationService.findById(id);

			if (personalInformation.isPresent()) {
				personalInformationService.remove(personalInformation.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
