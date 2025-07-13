package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permission create(Permission permission) {
		return permissaoRepository.saveAndFlush(permission);
	}
	
	public Permission atualiza(Permission permission) {
		return permissaoRepository.save(permission);
	}
	
	public void atualizaPermissao(Permission permission) {
		permissaoRepository.save(permission);
	}
	
	public List<Permission> findAll() {
		return permissaoRepository.findAll();
	}

	public Optional<Permission> findById(Long id) {
		return permissaoRepository.findById(id);
	}

	public List<Permission> findByPerfilId(Long perfilId) {
		return permissaoRepository.findByPerfilId(perfilId);
	}

	public void remove(Permission permission) {
		permissaoRepository.delete(permission);
	}

}
