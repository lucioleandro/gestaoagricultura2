package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityFarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityFarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityFarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityRepository;
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
class EconomicActivityFarmerServiceTest {

    @Mock
    private EconomicActivityFarmerRepository economicActivityRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private EconomicActivityRepository economicActivityRepo;

    @InjectMocks
    private EconomicActivityFarmerService service;

    private EconomicActivityFarmerRequest request;
    private EconomicActivityFarmer entity;
    private Farmer farmer;
    private Property property;
    private EconomicActivity economicActivity;

    @BeforeEach
    void setUp() {
        farmer = new Farmer();
        farmer.setId(11L);
        property = new Property();
        property.setId(22L);
        economicActivity = new EconomicActivity();
        economicActivity.setId(33L);

        request = new EconomicActivityFarmerRequest();
        request.setFarmerId(farmer.getId());
        request.setPropertyId(property.getId());
        request.setEconomicActivityId(economicActivity.getId());
        request.setDataInicial(LocalDate.of(2025, 3, 1));
        request.setDataFinal(LocalDate.of(2025, 12, 31));
        request.setPrincipal(true);

        entity = new EconomicActivityFarmer();
        entity.setId(44L);
        entity.setDataInicial(request.getDataInicial());
        entity.setDataFinal(request.getDataFinal());
        entity.setPrincipal(request.getPrincipal());
        entity.setFarmer(farmer);
        entity.setProperty(property);
        entity.setEconomicActivity(economicActivity);
    }

    @Test
    void testCreateSuccess() {
        when(economicActivityRepository.save(any(EconomicActivityFarmer.class))).thenReturn(entity);

        EconomicActivityFarmerResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDataInicial(), response.getDataInicial());
        assertEquals(request.getDataFinal(), response.getDataFinal());
        assertEquals(request.getPrincipal(), response.getPrincipal());
        assertEquals(farmer.getId() ,response.getFarmerId());

        verify(economicActivityRepository).save(any(EconomicActivityFarmer.class));
    }

    @Test
    void testUpdateSuccess() {
        when(economicActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(request.getFarmerId())).thenReturn(Optional.of(farmer));
        when(propertyRepository.findById(request.getPropertyId())).thenReturn(Optional.of(property));
        when(economicActivityRepo.findById(request.getEconomicActivityId())).thenReturn(Optional.of(economicActivity));
        when(economicActivityRepository.save(any(EconomicActivityFarmer.class))).thenReturn(entity);

        EconomicActivityFarmerResponse response = service.update(entity.getId(), request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDataInicial(), response.getDataInicial());
        assertEquals(request.getDataFinal(), response.getDataFinal());
        assertEquals(request.getPrincipal(), response.getPrincipal());
        assertEquals(farmer.getId(), response.getFarmerId());
        assertEquals(property.getId(), response.getPropertyId());
        assertEquals(economicActivity.getId(), response.getEconomicActivityId());

        verify(economicActivityRepository).findById(entity.getId());
        verify(farmerRepository).findById(request.getFarmerId());
        verify(propertyRepository).findById(request.getPropertyId());
        verify(economicActivityRepo).findById(request.getEconomicActivityId());
        verify(economicActivityRepository).save(any(EconomicActivityFarmer.class));
    }

    @Test
    void testUpdateNotFound() {
        when(economicActivityRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("Economic activity farmer not found"));
    }

    @Test
    void testUpdateFarmerNotFound() {
        when(economicActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(request.getFarmerId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }

    @Test
    void testUpdatePropertyNotFound() {
        when(economicActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(request.getFarmerId())).thenReturn(Optional.of(farmer));
        when(propertyRepository.findById(request.getPropertyId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Property not found"));
    }

    @Test
    void testUpdateEconomicActivityNotFound() {
        when(economicActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(request.getFarmerId())).thenReturn(Optional.of(farmer));
        when(propertyRepository.findById(request.getPropertyId())).thenReturn(Optional.of(property));
        when(economicActivityRepo.findById(request.getEconomicActivityId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Economic activity not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(economicActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        EconomicActivityFarmerResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(economicActivityRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("Economic activity farmer not found"));
    }

    @Test
    void testFindAll() {
        List<EconomicActivityFarmer> list = Arrays.asList(entity);
        when(economicActivityRepository.findAll()).thenReturn(list);
        List<EconomicActivityFarmerResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByFarmer() {
        List<EconomicActivityFarmer> list = Arrays.asList(entity);
        when(economicActivityRepository.findByFarmerId(farmer.getId())).thenReturn(list);
        List<EconomicActivityFarmerResponse> responses = service.findByFarmer(farmer.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByProperty() {
        List<EconomicActivityFarmer> list = Arrays.asList(entity);
        when(economicActivityRepository.findByPropertyId(property.getId())).thenReturn(list);
        List<EconomicActivityFarmerResponse> responses = service.findByProperty(property.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        when(economicActivityRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        doNothing().when(economicActivityRepository).delete(entity);
        service.remove(entity.getId());
        verify(economicActivityRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(economicActivityRepository.findById(555L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(555L));
        assertTrue(ex.getMessage().contains("Economic activity farmer not found"));
    }
}

