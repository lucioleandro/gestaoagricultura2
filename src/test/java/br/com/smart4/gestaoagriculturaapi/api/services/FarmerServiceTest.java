package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.FarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.FarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
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
class FarmerServiceTest {

    @Mock
    private FarmerRepository farmerRepository;

    @InjectMocks
    private FarmerService service;

    private FarmerRequest request;
    private Farmer entity;

    @BeforeEach
    void setUp() {
        request = new FarmerRequest();
        request.setNome("John Doe");
        request.setCpf("123.456.789-00");
        request.setRg("MG-12.345.678");
        request.setOrgaoExpeditor("SSP");
        request.setApelido("Johnny");
        request.setDataNascimento(LocalDate.of(1985, 5, 20));

        entity = new Farmer();
        entity.setId(100L);
        entity.setNome(request.getNome());
        entity.setCpf(request.getCpf());
        entity.setRg(request.getRg());
        entity.setOrgaoExpeditor(request.getOrgaoExpeditor());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
    }

    @Test
    void testCreate() {
        when(farmerRepository.save(any(Farmer.class))).thenReturn(entity);

        FarmerResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getNome(), response.getNome());
        assertEquals(request.getCpf(), response.getCpf());
        assertEquals(request.getRg(), response.getRg());
        assertEquals(request.getOrgaoExpeditor(), response.getOrgaoExpeditor());
        assertEquals(request.getApelido(), response.getApelido());
        assertEquals(request.getDataNascimento(), response.getDataNascimento());

        verify(farmerRepository).save(any(Farmer.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(farmerRepository.findById(id)).thenReturn(Optional.of(entity));
        Farmer updatedEntity = new Farmer();
        updatedEntity.setId(id);
        updatedEntity.setNome("Jane Doe");
        updatedEntity.setCpf("987.654.321-00");
        updatedEntity.setRg("SP-87.654.321");
        updatedEntity.setOrgaoExpeditor("DETRAN");
        updatedEntity.setApelido("Janey");
        updatedEntity.setDataNascimento(LocalDate.of(1990, 10, 15));

        when(farmerRepository.save(any(Farmer.class))).thenReturn(updatedEntity);

        FarmerRequest updateRequest = new FarmerRequest();
        updateRequest.setNome(updatedEntity.getNome());
        updateRequest.setCpf(updatedEntity.getCpf());
        updateRequest.setRg(updatedEntity.getRg());
        updateRequest.setOrgaoExpeditor(updatedEntity.getOrgaoExpeditor());
        updateRequest.setApelido(updatedEntity.getApelido());
        updateRequest.setDataNascimento(updatedEntity.getDataNascimento());

        FarmerResponse response = service.update(id, updateRequest);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(updatedEntity.getNome(), response.getNome());
        assertEquals(updatedEntity.getCpf(), response.getCpf());
        assertEquals(updatedEntity.getRg(), response.getRg());
        assertEquals(updatedEntity.getOrgaoExpeditor(), response.getOrgaoExpeditor());
        assertEquals(updatedEntity.getApelido(), response.getApelido());
        assertEquals(updatedEntity.getDataNascimento(), response.getDataNascimento());

        verify(farmerRepository).findById(id);
        verify(farmerRepository).save(any(Farmer.class));
    }

    @Test
    void testUpdateNotFound() {
        when(farmerRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(farmerRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        FarmerResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(farmerRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }

    @Test
    void testFindAll() {
        List<Farmer> list = Arrays.asList(entity);
        when(farmerRepository.findAll()).thenReturn(list);

        List<FarmerResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByCpfSuccess() {
        when(farmerRepository.findByCpf(entity.getCpf())).thenReturn(Optional.of(entity));
        FarmerResponse response = service.findByCpf(entity.getCpf());
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getCpf(), response.getCpf());
    }

    @Test
    void testFindByCpfNotFound() {
        when(farmerRepository.findByCpf("000.000.000-00")).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findByCpf("000.000.000-00"));
        assertTrue(ex.getMessage().contains("Farmer not found with CPF"));
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(farmerRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(farmerRepository).delete(entity);

        service.remove(id);

        verify(farmerRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(farmerRepository.findById(321L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(321L));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }
}

