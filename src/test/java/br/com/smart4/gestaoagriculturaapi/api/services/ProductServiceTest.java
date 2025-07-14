package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductRepository;
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
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService service;

    private ProductRequest request;
    private Product entity;

    @BeforeEach
    void setUp() {
        request = new ProductRequest();
        request.setDescricao("Produto A");
        request.setUnidadeMedida("Kilogram");
        request.setSiglaUnidadeMedida("kg");

        entity = new Product();
        entity.setId(200L);
        entity.setDescricao(request.getDescricao());
        entity.setUnidadeMedida(request.getUnidadeMedida());
        entity.setSiglaUnidadeMedida(request.getSiglaUnidadeMedida());
    }

    @Test
    void testCreate() {
        when(productRepository.save(any(Product.class))).thenReturn(entity);

        ProductResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDescricao(), response.getDescricao());
        assertEquals(request.getUnidadeMedida(), response.getUnidadeMedida());
        assertEquals(request.getSiglaUnidadeMedida(), response.getSiglaUnidadeMedida());

        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(productRepository.findById(id)).thenReturn(Optional.of(entity));

        Product updated = new Product();
        updated.setId(id);
        updated.setDescricao("Produto B");
        updated.setUnidadeMedida("Liter");
        updated.setSiglaUnidadeMedida("L");
        when(productRepository.save(any(Product.class))).thenReturn(updated);

        ProductRequest updateRequest = new ProductRequest();
        updateRequest.setDescricao(updated.getDescricao());
        updateRequest.setUnidadeMedida(updated.getUnidadeMedida());
        updateRequest.setSiglaUnidadeMedida(updated.getSiglaUnidadeMedida());

        ProductResponse response = service.update(id, updateRequest);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(updated.getDescricao(), response.getDescricao());
        assertEquals(updated.getUnidadeMedida(), response.getUnidadeMedida());
        assertEquals(updated.getSiglaUnidadeMedida(), response.getSiglaUnidadeMedida());

        verify(productRepository).findById(id);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testUpdateNotFound() {
        when(productRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("Product not found with id"));
    }

    @Test
    void testFindByIdSuccess() {
        when(productRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        ProductResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("Product not found with id"));
    }

    @Test
    void testFindAll() {
        List<Product> list = Arrays.asList(entity);
        when(productRepository.findAll()).thenReturn(list);

        List<ProductResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(productRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(productRepository).delete(entity);

        service.remove(id);

        verify(productRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(productRepository.findById(321L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(321L));
        assertTrue(ex.getMessage().contains("Product not found with id"));
    }
}

