package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserPictureRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserPictureResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserPictureRepository;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserRepository;
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
class UserPictureServiceTest {

    @Mock
    private UserPictureRepository userPictureRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserPictureService service;

    private UserPictureRequest request;
    private UserPicture entity;

    @BeforeEach
    void setUp() {
        request = new UserPictureRequest();
        request.setUserId(5L);
        request.setFotoPerfil("new byte[]{1,2,3}");

        entity = new UserPicture();
        entity.setId(10L);
        entity.setUsuario(User.builder().id(request.getUserId()).build());
        entity.setFotoPerfil(request.getFotoPerfil());
    }

    @Test
    void testCreateSuccess() {
        when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(mock(User.class)));
        when(userPictureRepository.saveAndFlush(any(UserPicture.class))).thenReturn(entity);

        UserPictureResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getFotoPerfil(), response.getFotoPerfil());
        verify(userRepository).findById(request.getUserId());
        verify(userPictureRepository).saveAndFlush(any(UserPicture.class));
    }

    @Test
    void testCreateUserNotFound() {
        when(userRepository.findById(request.getUserId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> service.create(request));
        assertTrue(ex.getMessage().contains("User not found with id"));
        verify(userPictureRepository, never()).saveAndFlush(any());
    }

    @Test
    void testUpdateSuccess() {
        when(userPictureRepository.findByUsuarioId(request.getUserId())).thenReturn(Optional.of(entity));
        when(userPictureRepository.save(any(UserPicture.class))).thenReturn(entity);

        UserPictureResponse response = service.update(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getFotoPerfil(), response.getFotoPerfil());
        verify(userPictureRepository).findByUsuarioId(request.getUserId());
        verify(userPictureRepository).save(any(UserPicture.class));
    }

    @Test
    void testUpdateNotFound() {
        when(userPictureRepository.findByUsuarioId(request.getUserId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> service.update(request));
        assertTrue(ex.getMessage().contains("UserPicture not found for user id"));
        verify(userPictureRepository, never()).save(any());
    }

    @Test
    void testFindAll() {
        List<UserPicture> list = Arrays.asList(entity);
        when(userPictureRepository.findAll()).thenReturn(list);

        List<UserPictureResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByIdSuccess() {
        when(userPictureRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        UserPictureResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(userPictureRepository.findById(99L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> service.findById(99L));
        assertTrue(ex.getMessage().contains("UserPicture not found with id"));
    }

    @Test
    void testFindByUsuarioLoginSuccess() {
        when(userPictureRepository.findByUsuarioLogin("login")).thenReturn(Optional.of(entity));
        UserPictureResponse response = service.findByUsuarioLogin("login");
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByUsuarioLoginNotFound() {
        when(userPictureRepository.findByUsuarioLogin("login")).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> service.findByUsuarioLogin("login"));
        assertTrue(ex.getMessage().contains("UserPicture not found for login"));
    }

    @Test
    void testRemoveSuccess() {
        when(userPictureRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doNothing().when(userPictureRepository).delete(entity);

        service.remove(entity.getId());

        verify(userPictureRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(userPictureRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> service.remove(123L));
        assertTrue(ex.getMessage().contains("UserPicture not found with id"));
    }
}
