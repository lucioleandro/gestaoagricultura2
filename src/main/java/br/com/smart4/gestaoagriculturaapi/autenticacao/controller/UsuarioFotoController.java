package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Usuario;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UsuarioFoto;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.UsuarioFotoService;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuariofoto")
public class UsuarioFotoController {

	@Autowired
	private UsuarioFotoService usuarioFotoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraUsuarioFoto(@RequestBody UsuarioFoto request) {
		try {
			return ResponseEntity.created(null).body(usuarioFotoService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PatchMapping("/atualiza")
	public ResponseEntity<?> atualizaUsuarioFoto(@RequestParam("fotoPerfil") String fotoPerfil,
												 @RequestParam("login") String login) {

		try {
			Optional<UsuarioFoto> usuarioFotoOptional = usuarioFotoService.findByUsuarioLogin(login);
			Optional<Usuario> usuarioOptional = usuarioService.findByLogin(login);
			
			UsuarioFoto usuarioFoto = null;
			
			if(usuarioOptional.isPresent() && usuarioFotoOptional.isPresent()) {
				usuarioFoto = usuarioFotoOptional.get();
				
				usuarioFoto.setFotoPerfil(fotoPerfil);
				usuarioFoto.setUsuario(usuarioOptional.get());
			
			} else if(!usuarioFotoOptional.isPresent() && usuarioOptional.isPresent()) {
				usuarioFoto = new UsuarioFoto(fotoPerfil, usuarioOptional.get());
			}
			
			return ResponseEntity.ok().body(usuarioFotoService.createOrAtualiza(usuarioFoto));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<UsuarioFoto> getListaUsuarioFoto() {
		try {
			return usuarioFotoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/fotobyusuario")
	public UsuarioFoto getFotoByUsuario(@Param(value="login") String login) {
		try {
			Optional<UsuarioFoto> usuarioOptional = usuarioFotoService.findByUsuarioLogin(login);
			
			if(usuarioOptional.isPresent()) {
				return usuarioOptional.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeUsuarioFoto(@PathVariable Long id) {
		try {
			Optional<UsuarioFoto> usuarioFoto = usuarioFotoService.findById(id);

			if (usuarioFoto.isPresent()) {
				usuarioFotoService.remove(usuarioFoto.get());
				return ResponseEntity.ok().body("");
			} 
			else if (!usuarioFoto.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
