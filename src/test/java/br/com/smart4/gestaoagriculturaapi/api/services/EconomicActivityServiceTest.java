package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityRepository;
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
class EconomicActivityServiceTest {

    @Mock
    private EconomicActivityRepository economicActivityRepository;

    @InjectMocks
    private EconomicActivityService service;

    private EconomicActivityRequest request;
    private EconomicActivity entity;

    @BeforeEach
    void setUp() {
        request = new EconomicActivityRequest();
        request.setCodigocnae("1234");
        request.setDescricao("Desc");
        request.setObservacao("Obs");
        request.setSituacao(true);
        request.setAliquota(5.5);
        request.setValor(100.0);
        request.setIsentoiss(true);
        request.setAtividadeDeServico(false);

        entity = new EconomicActivity();
        entity.setId(50L);
        entity.setCodigocnae(request.getCodigocnae());
        entity.setDescricao(request.getDescricao());
        entity.setObservacao(request.getObservacao());
        entity.setSituacao(request.getSituacao());
        entity.setAliquota(request.getAliquota());
        entity.setValor(request.getValor());
        entity.setIsentoiss(request.isIsentoiss());
        entity.setAtividadeDeServico(request.getAtividadeDeServico());
    }

    @Test
    void testCreate() {
        when(economicActivityRepository.save(any(EconomicActivity.class))).thenReturn(entity);

        EconomicActivityResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getCodigocnae(), response.getCodigocnae());
        assertEquals(request.getDescricao(), response.getDescricao());
        assertEquals(request.getObservacao(), response.getObservacao());
        assertEquals(request.getSituacao(), response.getSituacao());
        assertEquals(request.getAliquota(), response.getAliquota());
        assertEquals(request.getValor(), response.getValor());
        assertEquals(request.isIsentoiss(), response.isIsentoiss());
        assertEquals(request.getAtividadeDeServico(), response.getAtividadeDeServico());

        verify(economicActivityRepository).save(any(EconomicActivity.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(economicActivityRepository.findById(id)).thenReturn(Optional.of(entity));
        // prepare updated entity
        EconomicActivity updated = new EconomicActivity();
        updated.setId(id);
        updated.setCodigocnae("5678");
        updated.setDescricao("NewDesc");
        updated.setObservacao("NewObs");
        updated.setSituacao(false);
        updated.setAliquota(10.0);
        updated.setValor(200.0);
        updated.setIsentoiss(false);
        updated.setAtividadeDeServico(true);

        when(economicActivityRepository.save(any(EconomicActivity.class))).thenReturn(updated);

        EconomicActivityRequest updateRequest = new EconomicActivityRequest();
        updateRequest.setCodigocnae(updated.getCodigocnae());
        updateRequest.setDescricao(updated.getDescricao());
        updateRequest.setObservacao(updated.getObservacao());
        updateRequest.setSituacao(updated.getSituacao());
        updateRequest.setAliquota(updated.getAliquota());
        updateRequest.setValor(updated.getValor());
        updateRequest.setIsentoiss(updated.isIsentoiss());
        updateRequest.setAtividadeDeServico(updated.getAtividadeDeServico());

        EconomicActivityResponse response = service.update(id, updateRequest);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(updated.getCodigocnae(), response.getCodigocnae());
        assertEquals(updated.getDescricao(), response.getDescricao());
        assertEquals(updated.getObservacao(), response.getObservacao());
        assertEquals(updated.getSituacao(), response.getSituacao());
        assertEquals(updated.getAliquota(), response.getAliquota());
        assertEquals(updated.getValor(), response.getValor());
        assertEquals(updated.isIsentoiss(), response.isIsentoiss());
        assertEquals(updated.getAtividadeDeServico(), response.getAtividadeDeServico());

        verify(economicActivityRepository).findById(id);
        verify(economicActivityRepository).save(any(EconomicActivity.class));
    }

    @Test
    void testUpdateNotFound() {
        when(economicActivityRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("Economic activity not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(economicActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        EconomicActivityResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(economicActivityRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("Economic activity not found"));
    }

    @Test
    void testFindAll() {
        List<EconomicActivity> list = Arrays.asList(entity);
        when(economicActivityRepository.findAll()).thenReturn(list);

        List<EconomicActivityResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(economicActivityRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(economicActivityRepository).delete(entity);

        service.remove(id);

        verify(economicActivityRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(economicActivityRepository.findById(321L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(321L));
        assertTrue(ex.getMessage().contains("Economic activity not found"));
    }
}

