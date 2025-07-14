package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.NeighborhoodRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.NeighborhoodResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.NeighborhoodRepository;
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
class NeighborhoodServiceTest {

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private NeighborhoodService service;

    private NeighborhoodRequest request;
    private Neighborhood entity;
    private City city;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(10L);
        city.setNome("CityName");

        request = new NeighborhoodRequest();
        request.setNome("NeighborhoodName");
        request.setZona("Zona 1");
        request.setCityId(city.getId());

        entity = new Neighborhood();
        entity.setId(20L);
        entity.setNome(request.getNome());
        entity.setZona(request.getZona());
        entity.setCity(city);
    }

    @Test
    void testCreate() {
        when(neighborhoodRepository.save(any(Neighborhood.class))).thenReturn(entity);

        NeighborhoodResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getNome(), response.getNome());
        assertEquals(request.getZona(), response.getZona());
        assertEquals(city.getId(), response.getCityId());
        verify(neighborhoodRepository).save(any(Neighborhood.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(neighborhoodRepository.findById(id)).thenReturn(Optional.of(entity));
        when(cityRepository.findById(city.getId())).thenReturn(Optional.of(city));

        Neighborhood updatedEntity = new Neighborhood();
        updatedEntity.setId(id);
        updatedEntity.setNome("NewName");
        updatedEntity.setZona("Zona 2");
        updatedEntity.setCity(city);
        when(neighborhoodRepository.save(any(Neighborhood.class))).thenReturn(updatedEntity);

        NeighborhoodRequest updateRequest = new NeighborhoodRequest();
        updateRequest.setNome(updatedEntity.getNome());
        updateRequest.setZona(updatedEntity.getZona());
        updateRequest.setCityId(city.getId());

        NeighborhoodResponse response = service.update(id, updateRequest);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(updatedEntity.getNome(), response.getNome());
        assertEquals(updatedEntity.getZona(), response.getZona());
        assertEquals(city.getId(), response.getCityId());
        verify(neighborhoodRepository).findById(id);
        verify(cityRepository).findById(city.getId());
        verify(neighborhoodRepository).save(any(Neighborhood.class));
    }

    @Test
    void testUpdateNotFound() {
        when(neighborhoodRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("Neighborhood not found"));
    }

    @Test
    void testUpdateCityNotFound() {
        when(neighborhoodRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(cityRepository.findById(city.getId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("City not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(neighborhoodRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        NeighborhoodResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(neighborhoodRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("Neighborhood not found"));
    }

    @Test
    void testFindAll() {
        List<Neighborhood> list = Arrays.asList(entity);
        when(neighborhoodRepository.findAll()).thenReturn(list);
        List<NeighborhoodResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByMunicipioNome() {
        List<Neighborhood> list = Arrays.asList(entity);
        when(neighborhoodRepository.findByCityNome(city.getNome())).thenReturn(list);
        List<NeighborhoodResponse> responses = service.findByMunicipioNome(city.getNome());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(neighborhoodRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(neighborhoodRepository).delete(entity);

        service.remove(id);

        verify(neighborhoodRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(neighborhoodRepository.findById(321L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(321L));
        assertTrue(ex.getMessage().contains("Neighborhood not found"));
    }
}
