package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Benefit;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.BenefitRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.BenefitResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.BenefitRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BenefitServiceTest {

    @Mock
    private BenefitRepository benefitRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @InjectMocks
    private BenefitService service;

    private BenefitRequest request;
    private Farmer farmer;
    private Benefit benefit;

    @BeforeEach
    void setUp() {
        farmer = new Farmer();
        farmer.setId(7L);

        request = new BenefitRequest();
        request.setDescricao("Test Benefit");
        request.setDataConcedimento(LocalDateTime.of(2025, 6, 15, 10, 5));
        request.setBeneficiadoId(farmer.getId());

        benefit = new Benefit();
        benefit.setId(30L);
        benefit.setDescricao(request.getDescricao());
        benefit.setDataConcedimento(request.getDataConcedimento());
        benefit.setBeneficiado(farmer);
    }

    @Test
    void testCreateSuccess() {
        when(farmerRepository.findById(farmer.getId())).thenReturn(Optional.of(farmer));
        when(benefitRepository.save(any(Benefit.class))).thenReturn(benefit);

        BenefitResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(benefit.getId(), response.getId());
        assertEquals(request.getDescricao(), response.getDescricao());
        assertEquals(request.getDataConcedimento(), response.getDataConcedimento());
        assertEquals(farmer.getId(), response.getBeneficiadoId());

        verify(farmerRepository).findById(farmer.getId());
        verify(benefitRepository).save(any(Benefit.class));
    }

    @Test
    void testCreateFarmerNotFound() {
        when(farmerRepository.findById(farmer.getId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.create(request));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }

    @Test
    void testUpdateSuccess() {
        when(benefitRepository.findById(benefit.getId())).thenReturn(Optional.of(benefit));
        when(farmerRepository.findById(farmer.getId())).thenReturn(Optional.of(farmer));
        when(benefitRepository.save(any(Benefit.class))).thenReturn(benefit);

        BenefitResponse response = service.update(benefit.getId(), request);

        assertNotNull(response);
        assertEquals(benefit.getId(), response.getId());
        assertEquals(request.getDescricao(), response.getDescricao());
        assertEquals(request.getDataConcedimento(), response.getDataConcedimento());
        assertEquals(farmer.getId(), response.getBeneficiadoId());

        verify(benefitRepository).findById(benefit.getId());
        verify(farmerRepository).findById(request.getBeneficiadoId());
        verify(benefitRepository).save(any(Benefit.class));
    }

    @Test
    void testUpdateNotFound() {
        when(benefitRepository.findById(555L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(555L, request));
        assertTrue(ex.getMessage().contains("Benefit not found"));
    }

    @Test
    void testUpdateFarmerNotFound() {
        when(benefitRepository.findById(benefit.getId())).thenReturn(Optional.of(benefit));
        when(farmerRepository.findById(request.getBeneficiadoId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(benefit.getId(), request));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(benefitRepository.findById(benefit.getId())).thenReturn(Optional.of(benefit));

        BenefitResponse response = service.findById(benefit.getId());
        assertEquals(benefit.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(benefitRepository.findById(888L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(888L));
        assertTrue(ex.getMessage().contains("Benefit not found"));
    }

    @Test
    void testFindAll() {
        List<Benefit> list = Arrays.asList(benefit);
        when(benefitRepository.findAll()).thenReturn(list);

        List<BenefitResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(benefit.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        when(benefitRepository.findById(benefit.getId())).thenReturn(Optional.of(benefit));
        doNothing().when(benefitRepository).delete(benefit);

        service.remove(benefit.getId());

        verify(benefitRepository).delete(benefit);
    }

    @Test
    void testRemoveNotFound() {
        when(benefitRepository.findById(999L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(999L));
        assertTrue(ex.getMessage().contains("Benefit not found"));
    }
}

