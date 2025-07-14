package br.com.smart4.gestaoagriculturaapi.sistema.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Compatible;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.CompatibleResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.repositories.CompatibleRepository;
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
class CompatibleServiceTest {

    @Mock
    private CompatibleRepository compatibleRepository;

    @InjectMocks
    private CompatibleService service;

    private Compatible domain;

    @BeforeEach
    void setUp() {
        domain = new Compatible();
        domain.setId(1L);
        domain.setCodSistema(39);
    }

    @Test
    void testCreate() {
        when(compatibleRepository.save(domain)).thenReturn(domain);

        CompatibleResponse response = service.create(domain);

        assertNotNull(response);
        assertEquals(domain.getId(), response.getId());
        assertEquals(domain.getCodSistema(), response.getCodSistema());
        verify(compatibleRepository).save(domain);
    }

    @Test
    void testUpdate() {
        when(compatibleRepository.save(domain)).thenReturn(domain);

        CompatibleResponse response = service.update(domain);

        assertNotNull(response);
        assertEquals(domain.getId(), response.getId());
        verify(compatibleRepository).save(domain);
    }

    @Test
    void testRemove() {
        doNothing().when(compatibleRepository).delete(domain);

        service.remove(domain);

        verify(compatibleRepository).delete(domain);
    }

    @Test
    void testFindByIdExists() {
        when(compatibleRepository.findById(domain.getId())).thenReturn(Optional.of(domain));

        Optional<CompatibleResponse> opt = service.findById(domain.getId());

        assertTrue(opt.isPresent());
        assertEquals(domain.getId(), opt.get().getId());
        verify(compatibleRepository).findById(domain.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(compatibleRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<CompatibleResponse> opt = service.findById(2L);

        assertTrue(opt.isEmpty());
        verify(compatibleRepository).findById(2L);
    }

    @Test
    void testFindAll() {
        when(compatibleRepository.findAll()).thenReturn(Arrays.asList(domain));

        List<CompatibleResponse> list = service.findAll();

        assertEquals(1, list.size());
        assertEquals(domain.getId(), list.get(0).getId());
        verify(compatibleRepository).findAll();
    }

    @Test
    void testFindByCodSistemaExists() {
        when(compatibleRepository.findByIdSistema(domain.getCodSistema())).thenReturn(Optional.of(domain));

        Optional<Compatible> opt = service.findByCodSistema(domain.getCodSistema());

        assertTrue(opt.isPresent());
        assertEquals(domain, opt.get());
        verify(compatibleRepository).findByIdSistema(domain.getCodSistema());
    }

    @Test
    void testFindByCodSistemaNotFound() {
        when(compatibleRepository.findByIdSistema(999)).thenReturn(Optional.empty());

        Optional<Compatible> opt = service.findByCodSistema(999);

        assertTrue(opt.isEmpty());
        verify(compatibleRepository).findByIdSistema(999);
    }
}
