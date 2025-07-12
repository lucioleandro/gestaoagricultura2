package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.smart4.gestaoagriculturaapi.api.util.ResponseMessage;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Perfil;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Permissao;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.PermissaoService;
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
@RequestMapping("/permissao")
public class PermissaoController {

	@Autowired
	private PermissaoService permissaoService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraPermissao(@RequestBody Permissao request) {
		try {
			if(componenteJaCadastradoParaOPerfil(request.getComponente(),request.getPerfil())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("Este componente já está vinculado a este perfil !"));
			}
			
			return ResponseEntity.created(null).body(permissaoService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(
				   new ResponseMessage("Não foi possível cadastrar o componente!"));
		}
	}
	
	private boolean componenteJaCadastradoParaOPerfil(String componente, Perfil perfil) {
		List<Permissao> perfis = permissaoService.findByPerfilId(perfil.getId());
		
		for(Permissao perf: perfis) {
			if(perf.getComponente().equals(componente)) {
				return true;
			}
		}
		
		return false;
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaPermissao(@RequestBody Permissao request) {
		try {
			if(componenteJaCadastradoParaOPerfil(request.getComponente(), request.getPerfil())) {
				return ResponseEntity.badRequest().body(
						   new ResponseMessage("Este componente já está vinculado a este perfil!"));
			}
			return ResponseEntity.ok().body(permissaoService.atualiza(request));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(
					   new ResponseMessage("Não foi possível cadastrar o componente!"));
		}
	}

	@GetMapping("/lista")
	public List<Permissao> getListaPermissao() {
		try {
			return permissaoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyperfil")
	public List<Permissao> getListaPermissao(@Param(value = "id") Long id) {
		try {
			return permissaoService.findByPerfilId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removePermissao(@PathVariable Long id) {
		try {
			Optional<Permissao> permissao = permissaoService.findById(id);

			if (permissao.isPresent()) {
				permissaoService.remove(permissao.get());
				return ResponseEntity.ok().body("");
			} 
			else if (!permissao.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
