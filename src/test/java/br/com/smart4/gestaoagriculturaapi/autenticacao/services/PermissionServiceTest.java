package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.PermissionRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.PermissionResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.PermissionRepository;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private PermissionService service;

    private PermissionRequest request;
    private Permission entity;
    private Profile profile;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setId(33L);

        request = new PermissionRequest();
        request.setPerfilId(profile.getId());
        request.setComponente("Component");
        request.setAtivo(true);
        request.setAtalho(false);
        request.setSomenteLeitura(false);

        entity = new Permission();
        entity.setId(44L);
        entity.setComponente(request.getComponente());
        entity.setAtivo(request.isAtivo());
        entity.setAtalho(request.isAtalho());
        entity.setSomenteLeitura(request.isSomenteLeitura());
        entity.setPerfil(profile);
    }

    @Test
    void testCreateSuccess() {
        when(profileRepository.findById(profile.getId())).thenReturn(Optional.of(profile));
        when(permissionRepository.saveAndFlush(any(Permission.class))).thenReturn(entity);

        PermissionResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getComponente(), response.getComponente());
        verify(profileRepository).findById(profile.getId());
        verify(permissionRepository).saveAndFlush(any(Permission.class));
    }

    @Test
    void testCreateProfileNotFound() {
        when(profileRepository.findById(profile.getId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.create(request));
        assertTrue(ex.getMessage().contains("Profile not found with id"));
        verify(permissionRepository, never()).saveAndFlush(any());
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(permissionRepository.findById(id)).thenReturn(Optional.of(entity));
        when(profileRepository.findById(profile.getId())).thenReturn(Optional.of(profile));
        when(permissionRepository.save(any(Permission.class))).thenReturn(entity);

        PermissionResponse response = service.update(id, request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getComponente(), response.getComponente());
        verify(permissionRepository).findById(id);
        verify(profileRepository).findById(request.getPerfilId());
        verify(permissionRepository).save(any(Permission.class));
    }

    @Test
    void testUpdatePermissionNotFound() {
        when(permissionRepository.findById(99L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(99L, request));
        assertTrue(ex.getMessage().contains("Permission not found with id"));
        verify(profileRepository, never()).findById(any());
    }

    @Test
    void testUpdateProfileNotFound() {
        Long id = entity.getId();
        when(permissionRepository.findById(id)).thenReturn(Optional.of(entity));
        when(profileRepository.findById(request.getPerfilId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(id, request));
        assertTrue(ex.getMessage().contains("Profile not found with id"));
    }

    @Test
    void testFindAll() {
        List<Permission> list = Arrays.asList(entity);
        when(permissionRepository.findAll()).thenReturn(list);

        List<PermissionResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByIdSuccess() {
        when(permissionRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        PermissionResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(permissionRepository.findById(321L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(321L));
        assertTrue(ex.getMessage().contains("Permission not found with id"));
    }

    @Test
    void testFindByPerfilId() {
        List<Permission> list = Arrays.asList(entity);
        when(permissionRepository.findByPerfilId(profile.getId())).thenReturn(list);

        List<PermissionResponse> responses = service.findByPerfilId(profile.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(permissionRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(permissionRepository).delete(entity);

        service.remove(id);

        verify(permissionRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(permissionRepository.findById(888L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(888L));
        assertTrue(ex.getMessage().contains("Permission not found with id"));
    }
}

