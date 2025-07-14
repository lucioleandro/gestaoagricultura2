package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.domains.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.TitleDeedEnum;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.TitleDeedRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.TitleDeedResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.TitleDeedRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TitleDeedServiceTest {

    @Mock
    private TitleDeedRepository titleDeedRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private TitleDeedService service;

    private TitleDeedRequest request;
    private TitleDeed entity;
    private Property property;
    private MockMultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        property = new Property();
        property.setId(42L);

        file = new MockMultipartFile(
                "file", "deed.pdf", "application/pdf", "dummy".getBytes()
        );

        request = new TitleDeedRequest();
        request.setPropertyId(property.getId());
        request.setTitulo("Titulo");
        request.setObservacao("Obs");
        request.setDocumento(TitleDeedEnum.ITR);
        request.setFile(file);

        entity = new TitleDeed();
        entity.setId(7L);
        entity.setProperty(property);
        entity.setTitulo(request.getTitulo());
        entity.setObservacao(request.getObservacao());
        entity.setDocumento(request.getDocumento());
        entity.setBytes(file.getBytes());
        entity.setExtensao("pdf");
        entity.setDataAdicao(LocalDateTime.now());
    }

    @Test
    void testCreateSuccess() throws IOException {
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.of(property));
        when(titleDeedRepository.save(any(TitleDeed.class))).thenReturn(entity);

        TitleDeedResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals("pdf", response.getExtensao());
        verify(propertyRepository).findById(property.getId());
        verify(titleDeedRepository).save(any(TitleDeed.class));
    }

    @Test
    void testCreatePropertyNotFound() {
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.create(request));
        verify(propertyRepository).findById(property.getId());
        verify(titleDeedRepository, never()).save(any());
    }

    @Test
    void testUpdateSuccessNoPropertyChange() throws IOException {
        Long id = entity.getId();
        when(titleDeedRepository.findById(id)).thenReturn(Optional.of(entity));
        // same property id -> no lookup
        request.setPropertyId(property.getId());
        when(titleDeedRepository.save(any(TitleDeed.class))).thenReturn(entity);

        TitleDeedResponse response = service.update(id, request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals("pdf", response.getExtensao());
        verify(titleDeedRepository).findById(id);
        verify(propertyRepository, never()).findById(anyLong());
        verify(titleDeedRepository).save(any(TitleDeed.class));
    }

    @Test
    void testUpdateSuccessWithPropertyChange() throws IOException {
        Long id = entity.getId();
        Property newProp = new Property();
        newProp.setId(99L);
        when(titleDeedRepository.findById(id)).thenReturn(Optional.of(entity));
        // change property id
        request.setPropertyId(newProp.getId());
        when(propertyRepository.findById(newProp.getId())).thenReturn(Optional.of(newProp));
        when(titleDeedRepository.save(any(TitleDeed.class))).thenReturn(entity);

        TitleDeedResponse response = service.update(id, request);

        assertNotNull(response);
        verify(titleDeedRepository).findById(id);
        verify(propertyRepository).findById(newProp.getId());
        verify(titleDeedRepository).save(any(TitleDeed.class));
    }

    @Test
    void testUpdateNotFound() {
        when(titleDeedRepository.findById(123L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.update(123L, request));
        verify(titleDeedRepository).findById(123L);
    }

    @Test
    void testUpdatePropertyNotFound() {
        Long id = entity.getId();
        when(titleDeedRepository.findById(id)).thenReturn(Optional.of(entity));
        request.setPropertyId(555L);
        when(propertyRepository.findById(555L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.update(id, request));
        verify(propertyRepository).findById(555L);
    }

    @Test
    void testFindByIdExists() {
        when(titleDeedRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        Optional<TitleDeedResponse> opt = service.findById(entity.getId());
        assertTrue(opt.isPresent());
        assertEquals(entity.getId(), opt.get().getId());
    }

    @Test
    void testFindByIdNotExists() {
        when(titleDeedRepository.findById(321L)).thenReturn(Optional.empty());
        Optional<TitleDeedResponse> opt = service.findById(321L);
        assertTrue(opt.isEmpty());
    }

    @Test
    void testFindAll() {
        List<TitleDeed> list = Arrays.asList(entity);
        when(titleDeedRepository.findAll()).thenReturn(list);
        List<TitleDeedResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByProperty() {
        List<TitleDeed> list = Arrays.asList(entity);
        when(titleDeedRepository.findByPropertyId(property.getId())).thenReturn(list);
        List<TitleDeedResponse> responses = service.findByProperty(property.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemove() {
        service.remove(entity);
        verify(titleDeedRepository).delete(entity);
    }
}
