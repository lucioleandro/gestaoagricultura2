package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserProfileRepository;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserRepository;
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
class UserProfileServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private UserProfileService service;

    private UserProfileRequest request;
    private UserProfile entity;
    private User user;
    private Profile profile;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        profile = new Profile();
        profile.setId(2L);

        request = new UserProfileRequest();
        request.setAtivo(true);
        request.setAdministrador(false);
        request.setUsuarioId(user.getId());
        request.setPerfilId(profile.getId());

        entity = new UserProfile();
        entity.setId(10L);
        entity.setAtivo(request.isAtivo());
        entity.setAdministrador(request.isAdministrador());
        entity.setUsuario(user);
        entity.setPerfil(profile);
    }

    @Test
    void testCreate() {
        when(userProfileRepository.saveAndFlush(any(UserProfile.class))).thenReturn(entity);

        UserProfileResponse resp = service.create(request);

        assertNotNull(resp);
        assertEquals(entity.getId(), resp.getId());
        assertEquals(request.isAtivo(), resp.isAtivo());
        assertEquals(request.isAdministrador(), resp.isAdministrador());
        verify(userProfileRepository).saveAndFlush(any(UserProfile.class));
    }

    @Test
    void testUpdateSuccess() {
        when(userProfileRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(profileRepository.findById(profile.getId())).thenReturn(Optional.of(profile));
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(entity);

        UserProfileResponse resp = service.update(entity.getId(), request);

        assertNotNull(resp);
        assertEquals(entity.getId(), resp.getId());
        assertEquals(request.isAtivo(), resp.isAtivo());
        verify(userProfileRepository).findById(entity.getId());
        verify(userRepository).findById(request.getUsuarioId());
        verify(profileRepository).findById(request.getPerfilId());
        verify(userProfileRepository).save(any(UserProfile.class));
    }

    @Test
    void testUpdateNotFound() {
        when(userProfileRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.update(99L, request));
        verify(userProfileRepository).findById(99L);
    }

    @Test
    void testUpdateUserNotFound() {
        when(userProfileRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        verify(userRepository).findById(user.getId());
    }

    @Test
    void testUpdateProfileNotFound() {
        when(userProfileRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(profileRepository.findById(profile.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        verify(profileRepository).findById(profile.getId());
    }

    @Test
    void testFindAll() {
        List<UserProfile> list = Arrays.asList(entity);
        when(userProfileRepository.findAll()).thenReturn(list);
        List<UserProfileResponse> resp = service.findAll();
        assertEquals(1, resp.size());
        assertEquals(entity.getId(), resp.get(0).getId());
    }

    @Test
    void testFindByUserIdSuccess() {
        when(userProfileRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        UserProfileResponse resp = service.findByUserId(entity.getId());
        assertEquals(entity.getId(), resp.getId());
    }

    @Test
    void testFindByUserIdNotFound() {
        when(userProfileRepository.findById(55L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.findByUserId(55L));
    }

    @Test
    void testFindListByUserId() {
        List<UserProfile> list = Arrays.asList(entity);
        when(userProfileRepository.findByUsuarioId(user.getId())).thenReturn(list);
        List<UserProfileResponse> resp = service.findListByUserId(user.getId());
        assertEquals(1, resp.size());
        assertEquals(entity.getId(), resp.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        when(userProfileRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doNothing().when(userProfileRepository).delete(entity);
        service.remove(entity.getId());
        verify(userProfileRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(userProfileRepository.findById(88L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.remove(88L));
        verify(userProfileRepository).findById(88L);
    }
}

