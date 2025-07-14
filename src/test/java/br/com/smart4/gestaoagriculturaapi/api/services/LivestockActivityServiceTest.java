package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.SpeciesEnum;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.LivestockActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.LivestockActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.LivestockActivityRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
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
class LivestockActivityServiceTest {

    @Mock
    private LivestockActivityRepository livestockActivityRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private LivestockActivityService service;

    private LivestockActivityRequest request;
    private LivestockActivity entity;
    private Property property;

    @BeforeEach
    void setUp() {
        property = new Property();
        property.setId(101L);

        request = new LivestockActivityRequest();
        request.setEspecie(SpeciesEnum.BOVINO);
        request.setQuantidade(50);
        request.setRaca("Angus");
        request.setPropertyId(property.getId());

        entity = new LivestockActivity();
        entity.setId(202L);
        entity.setEspecie(request.getEspecie());
        entity.setQuantidade(request.getQuantidade());
        entity.setRaca(request.getRaca());
        entity.setProperty(property);
    }

    @Test
    void testCreate() {
        when(livestockActivityRepository.save(any(LivestockActivity.class))).thenReturn(entity);

        LivestockActivityResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getEspecie(), response.getEspecie());
        assertEquals(request.getQuantidade(), response.getQuantidade());
        assertEquals(request.getRaca(), response.getRaca());

        verify(livestockActivityRepository).save(any(LivestockActivity.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(livestockActivityRepository.findById(id)).thenReturn(Optional.of(entity));
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.of(property));

        LivestockActivity updatedEntity = new LivestockActivity();
        updatedEntity.setId(id);
        updatedEntity.setEspecie(SpeciesEnum.SUINO);
        updatedEntity.setQuantidade(30);
        updatedEntity.setRaca("Landrace");
        updatedEntity.setProperty(property);
        when(livestockActivityRepository.save(any(LivestockActivity.class))).thenReturn(updatedEntity);

        LivestockActivityRequest updateRequest = new LivestockActivityRequest();
        updateRequest.setEspecie(updatedEntity.getEspecie());
        updateRequest.setQuantidade(updatedEntity.getQuantidade());
        updateRequest.setRaca(updatedEntity.getRaca());
        updateRequest.setPropertyId(property.getId());

        LivestockActivityResponse response = service.update(id, updateRequest);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(updatedEntity.getEspecie(), response.getEspecie());
        assertEquals(updatedEntity.getQuantidade(), response.getQuantidade());
        assertEquals(updatedEntity.getRaca(), response.getRaca());
        assertEquals(property.getId(), response.getPropertyId());

        verify(livestockActivityRepository).findById(id);
        verify(propertyRepository).findById(property.getId());
        verify(livestockActivityRepository).save(any(LivestockActivity.class));
    }

    @Test
    void testUpdateNotFound() {
        when(livestockActivityRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("Livestock activity not found"));
    }

    @Test
    void testUpdatePropertyNotFound() {
        when(livestockActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Property not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(livestockActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        LivestockActivityResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(livestockActivityRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("Livestock activity not found"));
    }

    @Test
    void testFindAll() {
        List<LivestockActivity> list = Arrays.asList(entity);
        when(livestockActivityRepository.findAll()).thenReturn(list);
        List<LivestockActivityResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByPropertyId() {
        List<LivestockActivity> list = Arrays.asList(entity);
        when(livestockActivityRepository.findByPropertyId(property.getId())).thenReturn(list);
        List<LivestockActivityResponse> responses = service.findByPropertyId(property.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        when(livestockActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doNothing().when(livestockActivityRepository).delete(entity);

        service.remove(entity.getId());

        verify(livestockActivityRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(livestockActivityRepository.findById(321L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(321L));
        assertTrue(ex.getMessage().contains("Livestock activity not found"));
    }
}

