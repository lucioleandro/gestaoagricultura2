package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.smart4.gestaoagriculturaapi.api.util.ResponseMessage;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Usuario;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraUsuario(@RequestBody Usuario request) {
		try {
			new CPFValidator().assertValid(request.getCpf());
			
			String senhaCriptografada = new BCryptPasswordEncoder().encode(request.getPassword());
			request.setSenha(senhaCriptografada);
			
			return ResponseEntity.created(null).body(usuarioService.create(request));

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
	public ResponseEntity<?> atualizaUsuario(@RequestBody Usuario request) {
		try {
			new CPFValidator().assertValid(request.getCpf());
			
			String senhaCriptografada = new BCryptPasswordEncoder().encode(request.getPassword());
			request.setSenha(senhaCriptografada);
			
			return ResponseEntity.ok().body(usuarioService.atualiza(request));

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
	
	@PatchMapping("/atualizaprofile")
	public ResponseEntity<?> atualizaUsuario(@RequestParam("nome") String nome,
											 @RequestParam("login") String login,
											 @RequestParam("novologin") String novoLogin,
											 @RequestParam("email") String email) {
		try {
			Optional<Usuario> optionalUsuario = this.usuarioService.findByLogin(login);
				
			Usuario usuario = null;
			
			if(optionalUsuario.isPresent()) {
				usuario = optionalUsuario.get();
				usuario.setLogin(novoLogin);
				usuario.setNome(nome);
				usuario.setEmail(email);
			}
			
			return ResponseEntity.ok()
					.body(usuarioService.atualiza(usuario));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<Usuario> getListaUsuarioes() {
		try {
			return usuarioService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeUsuario(@PathVariable Long id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);

			if (usuario.isPresent()) {
				usuarioService.remove(usuario.get());
				return ResponseEntity.ok().body("");
			} 
			else if (!usuario.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PatchMapping("/atualizasenhausuario")
	public ResponseEntity<?> atualizaSenhaUsuario(@RequestParam("senhaAtual") String senhaAtual,
												  @RequestParam("novaSenha") String novaSenha,
												  @RequestParam("login") String login) {
		
		try {
			Optional<Usuario> optionalUsuario = usuarioService.findByLogin(login);
			Usuario usuario = null;
			
			if(optionalUsuario.isPresent()) {
				usuario = optionalUsuario.get();
				
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
				String novaSenhaCriptografada = encoder.encode(novaSenha);
				
				boolean senhaAtualEhValida = encoder.matches(senhaAtual, usuario.getSenha());
				
				if(senhaAtualEhValida) {
					usuario.setSenha(novaSenhaCriptografada);
					
					return ResponseEntity.ok().body(usuarioService.atualiza(usuario));
				}
				
				return ResponseEntity.badRequest().body(new ResponseMessage("A senha atual informada está incorreta"));
				
			} else {
				return ResponseEntity.badRequest().body(new ResponseMessage("Houve um erro inesperado, por favor, entre em contato com o suporte"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}

}
