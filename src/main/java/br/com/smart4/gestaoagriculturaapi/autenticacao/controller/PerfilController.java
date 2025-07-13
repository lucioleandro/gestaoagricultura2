package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.ProfileService;
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
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private ProfileService profileService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraPerfil(@RequestBody Profile request) {
		try {
			return ResponseEntity.created(null).body(profileService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaPerfil(@RequestBody Profile request) {
		try {
			return ResponseEntity.ok().body(profileService.atualiza(request));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<Profile> getListaPerfil() {
		try {
			return profileService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removePerfil(@PathVariable Long id) {
		try {
			Optional<Profile> perfil = profileService.findById(id);

			if (perfil.isPresent()) {
				profileService.remove(perfil.get());
				return ResponseEntity.ok().body("");
			} else if (!perfil.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
