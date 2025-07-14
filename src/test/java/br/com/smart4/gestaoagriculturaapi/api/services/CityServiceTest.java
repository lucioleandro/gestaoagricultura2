package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.CityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.CityResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.CityRepository;
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
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService service;

    private CityRequest request;
    private City city;

    @BeforeEach
    void setUp() {
        request = new CityRequest();
        request.setNome("Springfield");
        request.setUf("SP");
        request.setCadastroUnico(1);

        city = new City();
        city.setId(1L);
        city.setNome(request.getNome());
        city.setUf(request.getUf());
        city.setCadastroUnico(request.getCadastroUnico());
    }

    @Test
    void testCreate() {
        when(cityRepository.save(any(City.class))).thenReturn(city);

        CityResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(city.getId(), response.getId());
        assertEquals(city.getNome(), response.getNome());
        assertEquals(city.getUf(), response.getUf());
        assertEquals(city.getCadastroUnico(), response.getCadastroUnico());

        verify(cityRepository).save(any(City.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = city.getId();
        when(cityRepository.findById(id)).thenReturn(Optional.of(city));

        CityRequest updateRequest = new CityRequest();
        updateRequest.setNome("Shelbyville");
        updateRequest.setUf("SB");
        updateRequest.setCadastroUnico(1);

        City updated = new City();
        updated.setId(id);
        updated.setNome(updateRequest.getNome());
        updated.setUf(updateRequest.getUf());
        updated.setCadastroUnico(updateRequest.getCadastroUnico());

        when(cityRepository.save(any(City.class))).thenReturn(updated);

        CityResponse response = service.update(id, updateRequest);

        assertNotNull(response);
        assertEquals(updated.getId(), response.getId());
        assertEquals("Shelbyville", response.getNome());
        assertEquals("SB", response.getUf());
        assertEquals(1, response.getCadastroUnico());

        verify(cityRepository).findById(id);
        verify(cityRepository).save(any(City.class));
    }

    @Test
    void testUpdateNotFound() {
        when(cityRepository.findById(999L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("City not found with id"));
    }

    @Test
    void testFindByIdSuccess() {
        when(cityRepository.findById(city.getId())).thenReturn(Optional.of(city));

        CityResponse response = service.findById(city.getId());
        assertEquals(city.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(cityRepository.findById(2L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(2L));
        assertTrue(ex.getMessage().contains("City not found with id"));
    }

    @Test
    void testFindAll() {
        List<City> list = Arrays.asList(city);
        when(cityRepository.findAll()).thenReturn(list);

        List<CityResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(city.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = city.getId();
        when(cityRepository.findById(id)).thenReturn(Optional.of(city));
        doNothing().when(cityRepository).delete(city);

        service.remove(id);

        verify(cityRepository).delete(city);
    }

    @Test
    void testRemoveNotFound() {
        when(cityRepository.findById(3L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(3L));
        assertTrue(ex.getMessage().contains("City not found with id"));
    }
}

