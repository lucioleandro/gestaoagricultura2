package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.UserPictureService;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.UserService;
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
	private UserPictureService userPictureService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraUsuarioFoto(@RequestBody UserPicture request) {
		try {
			return ResponseEntity.created(null).body(userPictureService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PatchMapping("/atualiza")
	public ResponseEntity<?> atualizaUsuarioFoto(@RequestParam("fotoPerfil") String fotoPerfil,
												 @RequestParam("login") String login) {

		try {
			Optional<UserPicture> usuarioFotoOptional = userPictureService.findByUsuarioLogin(login);
			Optional<User> usuarioOptional = userService.findByLogin(login);
			
			UserPicture userPicture = null;
			
			if(usuarioOptional.isPresent() && usuarioFotoOptional.isPresent()) {
				userPicture = usuarioFotoOptional.get();
				
				userPicture.setFotoPerfil(fotoPerfil);
				userPicture.setUsuario(usuarioOptional.get());
			
			} else if(!usuarioFotoOptional.isPresent() && usuarioOptional.isPresent()) {
				userPicture = new UserPicture(fotoPerfil, usuarioOptional.get());
			}
			
			return ResponseEntity.ok().body(userPictureService.createOrAtualiza(userPicture));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<UserPicture> getListaUsuarioFoto() {
		try {
			return userPictureService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/fotobyusuario")
	public UserPicture getFotoByUsuario(@Param(value="login") String login) {
		try {
			Optional<UserPicture> usuarioOptional = userPictureService.findByUsuarioLogin(login);
			
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
			Optional<UserPicture> usuarioFoto = userPictureService.findById(id);

			if (usuarioFoto.isPresent()) {
				userPictureService.remove(usuarioFoto.get());
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
