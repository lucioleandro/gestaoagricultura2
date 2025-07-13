package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.UserProfileService;
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
@RequestMapping("/usuarioPerfil")
public class UsuarioPerfilController {

	@Autowired
	private UserProfileService userProfileService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraUsuarioPerfil(@RequestBody UserProfile request) {
		try {
			return ResponseEntity.created(null).body(userProfileService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<UserProfile> getListaUsuarioPerfil() {
		try {
			return userProfileService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/listabyusuario")
	public List<UserProfile> getListaUsuarioPerfiByUsuario(@Param(value="id") Long id) {
		try {
			return userProfileService.findByUsuarioId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeUsuarioPerfil(@PathVariable Long id) {
		try {
			Optional<UserProfile> usuarioPerfil = userProfileService.findById(id);

			if (usuarioPerfil.isPresent()) {
				userProfileService.remove(usuarioPerfil.get());
				return ResponseEntity.ok().body("");
			} 
			else if (!usuarioPerfil.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaUsuarioPerfil(@RequestBody UserProfile request) {
		try {
			return ResponseEntity.ok().body(userProfileService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
