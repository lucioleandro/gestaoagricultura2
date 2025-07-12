package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UsuarioPerfil;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.UsuarioPerfilService;
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
	private UsuarioPerfilService usuarioPerfilService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraUsuarioPerfil(@RequestBody UsuarioPerfil request) {
		try {
			return ResponseEntity.created(null).body(usuarioPerfilService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<UsuarioPerfil> getListaUsuarioPerfil() {
		try {
			return usuarioPerfilService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/listabyusuario")
	public List<UsuarioPerfil> getListaUsuarioPerfiByUsuario(@Param(value="id") Long id) {
		try {
			return usuarioPerfilService.findByUsuarioId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeUsuarioPerfil(@PathVariable Long id) {
		try {
			Optional<UsuarioPerfil> usuarioPerfil = usuarioPerfilService.findById(id);

			if (usuarioPerfil.isPresent()) {
				usuarioPerfilService.remove(usuarioPerfil.get());
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
	public ResponseEntity<?> atualizaUsuarioPerfil(@RequestBody UsuarioPerfil request) {
		try {
			return ResponseEntity.ok().body(usuarioPerfilService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
