package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.PermissionRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.PermissionResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.PermissionFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.PermissionMapper;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.PermissionRepository;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final ProfileRepository profileRepository;

    public PermissionService(PermissionRepository permissionRepository, ProfileRepository profileRepository) {
        this.permissionRepository = permissionRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public PermissionResponse create(PermissionRequest request) {
        profileRepository.findById(request.getPerfilId())
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + request.getPerfilId()));

        Permission entity = PermissionFactory.fromRequest(request);
        return PermissionMapper.toResponse(permissionRepository.saveAndFlush(entity));
    }

    @Transactional
    public PermissionResponse update(Long id, PermissionRequest request) {
        Permission entity = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id: " + id));

        Profile profile = profileRepository.findById(request.getPerfilId())
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + request.getPerfilId()));

        entity.setComponente(request.getComponente());
        entity.setAtivo(request.isAtivo());
        entity.setAtalho(request.isAtalho());
        entity.setSomenteLeitura(request.isSomenteLeitura());
        entity.setPerfil(profile);

        return PermissionMapper.toResponse(permissionRepository.save(entity));
    }

    public List<PermissionResponse> findAll() {
        return PermissionMapper.toListResponse(permissionRepository.findAll());
    }

    public PermissionResponse findById(Long id) {
        return permissionRepository.findById(id)
                .map(PermissionMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id: " + id));
    }

    public List<PermissionResponse> findByPerfilId(Long perfilId) {
        return PermissionMapper.toListResponse(permissionRepository.findByPerfilId(perfilId));
    }

    @Transactional
    public void remove(Long id) {
        Permission entity = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id: " + id));
        permissionRepository.delete(entity);
    }
}
