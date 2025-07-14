package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;

    private User entity;
    private UserRequest request;

    @BeforeEach
    void setUp() {
        entity = new User();
        entity.setId(50L);
        entity.setNome("Test User");
        entity.setEmail("user@test.com");
        entity.setLogin("testuser");
        entity.setSenha("password");

        request = new UserRequest();
        request.setNome(entity.getNome());
        request.setEmail(entity.getEmail());
        request.setLogin(entity.getLogin());
        request.setSenha(entity.getSenha());
    }

    @Test
    void testLoadUserByUsernameSuccess() {
        when(userRepository.findByName(entity.getLogin())).thenReturn(entity);

        UserDetails userDetails = service.loadUserByUsername(entity.getLogin());

        assertSame(entity, userDetails);
        verify(userRepository).findByName(entity.getLogin());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        when(userRepository.findByName("unknown")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () ->
                service.loadUserByUsername("unknown")
        );
        verify(userRepository).findByName("unknown");
    }

    @Test
    void testCreate() {
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(entity);

        UserResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getLogin(), response.getLogin());
        verify(userRepository).saveAndFlush(any(User.class));
    }

    @Test
    void testUpdateByIdSuccess() {
        when(userRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(userRepository.save(any(User.class))).thenReturn(entity);

        UserRequest updateReq = new UserRequest();
        updateReq.setNome("Updated");
        updateReq.setEmail("upd@test.com");
        updateReq.setLogin("updated");
        updateReq.setSenha("newpass");

        UserResponse response = service.update(entity.getId(), updateReq);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(updateReq.getLogin(), response.getLogin());
        verify(userRepository).findById(entity.getId());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testUpdateByIdNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                service.update(99L, request)
        );
        verify(userRepository).findById(99L);
    }

    @Test
    void testUpdateEntity() {
        when(userRepository.save(entity)).thenReturn(entity);

        UserResponse response = service.update(entity);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        verify(userRepository).save(entity);
    }

    @Test
    void testFindAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(entity));

        List<UserResponse> list = service.findAll();

        assertEquals(1, list.size());
        assertEquals(entity.getId(), list.get(0).getId());
        verify(userRepository).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        when(userRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        UserResponse response = service.findById(entity.getId());

        assertEquals(entity.getId(), response.getId());
        verify(userRepository).findById(entity.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(userRepository.findById(123L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                service.findById(123L)
        );
        verify(userRepository).findById(123L);
    }

    @Test
    void testFindByLoginExists() {
        when(userRepository.findByLogin(entity.getLogin())).thenReturn(Optional.of(entity));

        Optional<UserResponse> opt = service.findByLogin(entity.getLogin());

        assertTrue(opt.isPresent());
        assertEquals(entity.getLogin(), opt.get().getLogin());
        verify(userRepository).findByLogin(entity.getLogin());
    }

    @Test
    void testFindByLoginNotExists() {
        when(userRepository.findByLogin("nope")).thenReturn(Optional.empty());

        Optional<UserResponse> opt = service.findByLogin("nope");

        assertTrue(opt.isEmpty());
        verify(userRepository).findByLogin("nope");
    }

    @Test
    void testRemoveSuccess() {
        when(userRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doNothing().when(userRepository).delete(entity);

        service.remove(entity.getId());

        verify(userRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(userRepository.findById(456L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                service.remove(456L)
        );
        verify(userRepository).findById(456L);
    }
}

