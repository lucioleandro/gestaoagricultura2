package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.smart4.gestaoagriculturaapi.api.util.ResponseMessage;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.service.PermissionService;
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
	private PermissionService permissionService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraPermissao(@RequestBody Permission request) {
		try {
			if(componenteJaCadastradoParaOPerfil(request.getComponente(),request.getPerfil())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("Este componente já está vinculado a este perfil !"));
			}
			
			return ResponseEntity.created(null).body(permissionService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(
				   new ResponseMessage("Não foi possível cadastrar o componente!"));
		}
	}
	
	private boolean componenteJaCadastradoParaOPerfil(String componente, Profile perfil) {
		List<Permission> perfis = permissionService.findByPerfilId(perfil.getId());
		
		for(Permission perf: perfis) {
			if(perf.getComponente().equals(componente)) {
				return true;
			}
		}
		
		return false;
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaPermissao(@RequestBody Permission request) {
		try {
			if(componenteJaCadastradoParaOPerfil(request.getComponente(), request.getPerfil())) {
				return ResponseEntity.badRequest().body(
						   new ResponseMessage("Este componente já está vinculado a este perfil!"));
			}
			return ResponseEntity.ok().body(permissionService.atualiza(request));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(
					   new ResponseMessage("Não foi possível cadastrar o componente!"));
		}
	}

	@GetMapping("/lista")
	public List<Permission> getListaPermissao() {
		try {
			return permissionService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyperfil")
	public List<Permission> getListaPermissao(@Param(value = "id") Long id) {
		try {
			return permissionService.findByPerfilId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removePermissao(@PathVariable Long id) {
		try {
			Optional<Permission> permissao = permissionService.findById(id);

			if (permissao.isPresent()) {
				permissionService.remove(permissao.get());
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
