package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.PermissionRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.PermissionResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.PermissionFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.PermissionMapper;
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
    public PermissionResponse create(PermissionRequest permission) {
        Permission entity = permissionRepository.saveAndFlush(
                PermissionFactory.fromRequest(permission)
        );
        return PermissionMapper.toResponse(entity);
    }

    @Transactional
    public PermissionResponse update(PermissionRequest permission) {
        Permission entity = permissionRepository.save(
                PermissionFactory.fromRequest(permission)
        );
        return PermissionMapper.toResponse(entity);
    }

    public List<PermissionResponse> findAll() {
        return PermissionMapper.toListResponse(
                permissionRepository.findAll()
        );
    }

    public Optional<PermissionResponse> findById(Long id) {
        return permissionRepository.findById(id)
                .map(PermissionMapper::toResponse);
    }

    public List<PermissionResponse> findByPerfilId(Long perfilId) {
        return PermissionMapper.toListResponse(
                permissionRepository.findByPerfilId(perfilId)
        );
    }

    @Transactional
    public void remove(Permission permission) {
        permissionRepository.delete(permission);
    }
}
