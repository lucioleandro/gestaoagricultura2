package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.PermissionRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.PermissionFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

	private final PermissionRepository permissionRepository;

	public PermissionService(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

	@Transactional
	public Permission create(PermissionRequest permission) {
		return permissionRepository.saveAndFlush(PermissionFactory.fromRequest(permission));
	}

	@Transactional
	public Permission atualiza(PermissionRequest permission) {
		return permissionRepository.save(PermissionFactory.fromRequest(permission));
	}

	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	public Optional<Permission> findById(Long id) {
		return permissionRepository.findById(id);
	}

	public List<Permission> findByPerfilId(Long perfilId) {
		return permissionRepository.findByPerfilId(perfilId);
	}

	@Transactional
	public void remove(Permission permission) {
		permissionRepository.delete(permission);
	}

}
