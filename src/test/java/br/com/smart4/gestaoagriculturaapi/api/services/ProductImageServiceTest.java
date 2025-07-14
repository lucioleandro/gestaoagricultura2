package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.domains.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductImageRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductImageResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductImageRepository;
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
class ProductImageServiceTest {

    @Mock
    private ProductImageRepository productImageRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductImageService service;

    private ProductImageRequest request;
    private ProductImage entity;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(77L);

        request = new ProductImageRequest();
        request.setArquivo("file-data".getBytes());
        request.setExtensao(".jpg");
        request.setProductId(product.getId());

        entity = new ProductImage();
        entity.setId(88L);
        entity.setArquivo(request.getArquivo());
        entity.setExtensao(request.getExtensao());
        entity.setProduct(product);
    }

    @Test
    void testCreate() {
        when(productImageRepository.save(any(ProductImage.class))).thenReturn(entity);

        ProductImageResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getArquivo(), response.getArquivo());
        assertEquals(request.getExtensao(), response.getExtensao());
        assertEquals(product.getId(), response.getProductId());
        verify(productImageRepository).save(any(ProductImage.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(productImageRepository.findById(id)).thenReturn(Optional.of(entity));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ProductImage updated = new ProductImage();
        updated.setId(id);
        updated.setArquivo("new-file".getBytes());
        updated.setExtensao(".png");
        updated.setProduct(product);
        when(productImageRepository.save(any(ProductImage.class))).thenReturn(updated);

        ProductImageRequest updateRequest = new ProductImageRequest();
        updateRequest.setArquivo(updated.getArquivo());
        updateRequest.setExtensao(updated.getExtensao());
        updateRequest.setProductId(product.getId());

        ProductImageResponse response = service.update(id, updateRequest);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(updated.getArquivo(), response.getArquivo());
        assertEquals(updated.getExtensao(), response.getExtensao());
        assertEquals(product.getId(), response.getProductId());
        verify(productImageRepository).findById(id);
        verify(productRepository).findById(product.getId());
        verify(productImageRepository).save(any(ProductImage.class));
    }

    @Test
    void testUpdateNotFound() {
        when(productImageRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("ProductImage not found"));
    }

    @Test
    void testUpdateProductNotFound() {
        Long id = entity.getId();
        when(productImageRepository.findById(id)).thenReturn(Optional.of(entity));
        when(productRepository.findById(request.getProductId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(id, request));
        assertTrue(ex.getMessage().contains("Product not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(productImageRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        ProductImageResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(productImageRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("ProductImage not found"));
    }

    @Test
    void testFindAll() {
        List<ProductImage> list = Arrays.asList(entity);
        when(productImageRepository.findAll()).thenReturn(list);

        List<ProductImageResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByProductId() {
        List<ProductImage> list = Arrays.asList(entity);
        when(productImageRepository.findByProductId(product.getId())).thenReturn(list);

        List<ProductImageResponse> responses = service.findByProductId(product.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(productImageRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(productImageRepository).delete(entity);

        service.remove(id);

        verify(productImageRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(productImageRepository.findById(456L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(456L));
        assertTrue(ex.getMessage().contains("ProductImage not found"));
    }
}

