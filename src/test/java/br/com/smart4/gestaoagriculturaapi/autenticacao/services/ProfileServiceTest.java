package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.ProfileTypeEnum;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.SistemasMBEnum;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.ProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.ProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService service;

    private ProfileRequest request;
    private Profile entity;

    @BeforeEach
    void setUp() {
        request = new ProfileRequest();
        request.setNome("Admin");
        request.setDescricao("Administrador");
        request.setTipo(ProfileTypeEnum.ADMINISTRATOR);
        request.setSistema(SistemasMBEnum.SmartAgro);

        entity = new Profile();
        entity.setId(101L);
        entity.setNome(request.getNome());
        entity.setDescricao(request.getDescricao());
        entity.setTipo(request.getTipo());
        entity.setSistema(request.getSistema());
    }

    @Test
    void testCreate() {
        when(profileRepository.saveAndFlush(any(Profile.class))).thenReturn(entity);

        ProfileResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getNome(), response.getNome());
        verify(profileRepository).saveAndFlush(any(Profile.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(profileRepository.findById(id)).thenReturn(Optional.of(entity));
        when(profileRepository.save(any(Profile.class))).thenReturn(entity);

        ProfileRequest updateReq = new ProfileRequest();
        updateReq.setNome("User");
        updateReq.setDescricao("System User");
        updateReq.setTipo(ProfileTypeEnum.COMMON);
        updateReq.setSistema(SistemasMBEnum.SmartAgro);

        ProfileResponse response = service.update(id, updateReq);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(updateReq.getNome(), response.getNome());
        assertEquals(updateReq.getDescricao(), response.getDescricao());
        assertEquals(updateReq.getTipo(), response.getTipo());
        assertEquals(updateReq.getSistema(), response.getSistema());
        verify(profileRepository).findById(id);
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    void testUpdateNotFound() {
        when(profileRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        verify(profileRepository).findById(999L);
        verify(profileRepository, never()).save(any());
    }

    @Test
    void testFindAll() {
        List<Profile> list = Collections.singletonList(entity);
        when(profileRepository.findAll()).thenReturn(list);

        List<ProfileResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByIdSuccess() {
        when(profileRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        ProfileResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(profileRepository.findById(123L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        verify(profileRepository).findById(123L);
    }

    @Test
    void testFindByIdUsuario() {
        List<Profile> list = Arrays.asList(entity);
        when(profileRepository.findByUserId(55L)).thenReturn(list);

        List<ProfileResponse> responses = service.findByIdUsuario(55L);
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(profileRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(profileRepository).delete(entity);

        service.remove(id);

        verify(profileRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(profileRepository.findById(456L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.remove(456L));
        verify(profileRepository).findById(456L);
    }
}
