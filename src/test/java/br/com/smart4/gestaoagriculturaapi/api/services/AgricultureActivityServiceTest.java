package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.IrrigationMethodEnum;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.WaterSourceEnum;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AgricultureActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AgricultureActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AgricultureActivityRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AgricultureActivityServiceTest {

    @Mock
    private AgricultureActivityRepository atividadeAgricolaRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private AgricultureActivityService service;

    private AgricultureActivityRequest request;
    private AgricultureActivity entity;
    private Property property;
    private Product product;

    @BeforeEach
    void setUp() {
        property = new Property();
        property.setId(5L);
        product = new Product();
        product.setId(6L);

        request = new AgricultureActivityRequest();
        request.setPropertyId(property.getId());
        request.setProductId(product.getId());
        request.setDataPlantio(LocalDate.of(2025,1,1));
        request.setVariedade("VarA");
        request.setAreaPlantada(10.5);
        request.setQuantidadePlantas(100);
        request.setProducaoAnual(200.75);
        request.setMetodoIrrigacao(IrrigationMethodEnum.AC);
        request.setFonteAgua(WaterSourceEnum.R);

        entity = new AgricultureActivity();
        entity.setId(20L);
        entity.setDataPlantio(request.getDataPlantio());
        entity.setVariedade(request.getVariedade());
        entity.setAreaPlantada(request.getAreaPlantada());
        entity.setQuantidadePlantas(request.getQuantidadePlantas());
        entity.setProducaoAnual(request.getProducaoAnual());
        entity.setMetodoIrrigacao(request.getMetodoIrrigacao());
        entity.setFonteAgua(request.getFonteAgua());
        entity.setProperty(property);
        entity.setProduct(product);
    }

    @Test
    void testCreateSuccess() {
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.of(property));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(atividadeAgricolaRepository.save(any(AgricultureActivity.class))).thenReturn(entity);

        AgricultureActivityResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDataPlantio(), response.getDataPlantio());
        assertEquals(request.getVariedade(), response.getVariedade());
        assertEquals(request.getAreaPlantada(), response.getAreaPlantada());
        assertEquals(request.getQuantidadePlantas(), response.getQuantidadePlantas());
        assertEquals(request.getProducaoAnual(), response.getProducaoAnual());
        assertEquals(request.getMetodoIrrigacao(), response.getMetodoIrrigacao());
        assertEquals(request.getFonteAgua(), response.getFonteAgua());
        assertEquals(property.getId(), response.getPropertyId());
        assertEquals(product.getId(), response.getProductId());

        verify(propertyRepository).findById(property.getId());
        verify(productRepository).findById(product.getId());
        verify(atividadeAgricolaRepository).save(any(AgricultureActivity.class));
    }

    @Test
    void testCreatePropertyNotFound() {
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.create(request));
        assertTrue(ex.getMessage().contains("Property not found"));
    }

    @Test
    void testCreateProductNotFound() {
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.of(property));
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.create(request));
        assertTrue(ex.getMessage().contains("Product not found"));
    }

    @Test
    void testUpdateSuccess() {
        when(atividadeAgricolaRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.of(property));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(atividadeAgricolaRepository.save(any(AgricultureActivity.class))).thenReturn(entity);

        AgricultureActivityResponse response = service.update(entity.getId(), request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDataPlantio(), response.getDataPlantio());
        // other fields implicitly covered

        verify(atividadeAgricolaRepository).findById(entity.getId());
        verify(propertyRepository).findById(request.getPropertyId());
        verify(productRepository).findById(request.getProductId());
        verify(atividadeAgricolaRepository).save(any(AgricultureActivity.class));
    }

    @Test
    void testUpdateNotFound() {
        when(atividadeAgricolaRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("Agriculture activity not found"));
    }

    @Test
    void testUpdatePropertyNotFound() {
        when(atividadeAgricolaRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(propertyRepository.findById(request.getPropertyId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Property not found"));
    }

    @Test
    void testUpdateProductNotFound() {
        when(atividadeAgricolaRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(propertyRepository.findById(request.getPropertyId())).thenReturn(Optional.of(property));
        when(productRepository.findById(request.getProductId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Product not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(atividadeAgricolaRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        AgricultureActivityResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(atividadeAgricolaRepository.findById(111L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(111L));
        assertTrue(ex.getMessage().contains("Agriculture activity not found"));
    }

    @Test
    void testFindAll() {
        List<AgricultureActivity> list = Arrays.asList(entity);
        when(atividadeAgricolaRepository.findAll()).thenReturn(list);
        List<AgricultureActivityResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByProperty() {
        List<AgricultureActivity> list = Arrays.asList(entity);
        when(atividadeAgricolaRepository.findByPropertyId(property.getId())).thenReturn(list);
        List<AgricultureActivityResponse> responses = service.findByProperty(property.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        when(atividadeAgricolaRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doNothing().when(atividadeAgricolaRepository).delete(entity);
        service.remove(entity.getId());
        verify(atividadeAgricolaRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(atividadeAgricolaRepository.findById(222L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(222L));
        assertTrue(ex.getMessage().contains("Agriculture activity not found"));
    }
}
