package br.com.smart4.gestaoagriculturaapi.sistema.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Parameters;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.ParametersResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.repositories.ParametersRepository;
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
class ParametersServiceTest {

    @Mock
    private ParametersRepository parametersRepository;

    @InjectMocks
    private ParametersService service;

    private Parameters domain;

    @BeforeEach
    void setUp() {
        domain = new Parameters();
        domain.setId(1L);
    }

    @Test
    void testCreate() {
        when(parametersRepository.save(domain)).thenReturn(domain);

        ParametersResponse response = service.create(domain);

        assertNotNull(response);
        assertEquals(domain.getId(), response.getId());
        verify(parametersRepository).save(domain);
    }

    @Test
    void testUpdate() {
        when(parametersRepository.save(domain)).thenReturn(domain);

        ParametersResponse response = service.update(domain);

        assertNotNull(response);
        assertEquals(domain.getId(), response.getId());
        verify(parametersRepository).save(domain);
    }

    @Test
    void testFindByIdExists() {
        when(parametersRepository.findById(domain.getId())).thenReturn(Optional.of(domain));

        Optional<ParametersResponse> opt = service.findById(domain.getId());

        assertTrue(opt.isPresent());
        assertEquals(domain.getId(), opt.get().getId());
        verify(parametersRepository).findById(domain.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(parametersRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<ParametersResponse> opt = service.findById(2L);

        assertTrue(opt.isEmpty());
        verify(parametersRepository).findById(2L);
    }

    @Test
    void testFindAll() {
        when(parametersRepository.findAll()).thenReturn(Arrays.asList(domain));

        List<ParametersResponse> list = service.findAll();

        assertEquals(1, list.size());
        assertEquals(domain.getId(), list.get(0).getId());
        verify(parametersRepository).findAll();
    }

    @Test
    void testRemove() {
        doNothing().when(parametersRepository).delete(domain);

        service.remove(domain);

        verify(parametersRepository).delete(domain);
    }
}

