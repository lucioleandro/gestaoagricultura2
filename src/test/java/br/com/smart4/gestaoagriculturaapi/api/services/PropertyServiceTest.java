package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.LandRegularizationEnum;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PropertyRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PropertyResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AddressRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
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
class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private PropertyService service;

    private PropertyRequest request;
    private Property entity;
    private Farmer farmer;
    private Address address;

    @BeforeEach
    void setUp() {
        farmer = new Farmer();
        farmer.setId(5L);
        address = new Address();
        address.setId(6L);

        request = new PropertyRequest();
        request.setNome("Fazenda Verde");
        request.setItr("123.456");
        request.setIncra("789.012");
        request.setLatitude("-12.3456");
        request.setLongitude("65.4321");
        request.setAreaTotal(100.0);
        request.setAreaAgricola(80.0);
        request.setReservaLegal(20.0);
        request.setTipoResidencia("Casa Sede");
        request.setRegularizacaoFundiaria(LandRegularizationEnum.ESCRITURA);
        request.setFarmerId(farmer.getId());
        request.setAddressId(address.getId());

        entity = new Property();
        entity.setId(10L);
        entity.setNome(request.getNome());
        entity.setItr(request.getItr());
        entity.setIncra(request.getIncra());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setAreaTotal(request.getAreaTotal());
        entity.setAreaAgricola(request.getAreaAgricola());
        entity.setReservaLegal(request.getReservaLegal());
        entity.setTipoResidencia(request.getTipoResidencia());
        entity.setRegularizacaoFundiaria(request.getRegularizacaoFundiaria());
        entity.setFarmer(farmer);
        entity.setAddress(address);
    }

    @Test
    void testCreate() {
        when(propertyRepository.save(any(Property.class))).thenReturn(entity);

        PropertyResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getNome(), response.getNome());
        verify(propertyRepository).save(any(Property.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(propertyRepository.findById(id)).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(farmer.getId())).thenReturn(Optional.of(farmer));
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        when(propertyRepository.save(any(Property.class))).thenReturn(entity);

        PropertyResponse response = service.update(id, request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getNome(), response.getNome());
        verify(propertyRepository).findById(id);
        verify(farmerRepository).findById(request.getFarmerId());
        verify(addressRepository).findById(request.getAddressId());
        verify(propertyRepository).save(any(Property.class));
    }

    @Test
    void testUpdateNotFound() {
        when(propertyRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("Property not found"));
    }

    @Test
    void testUpdateFarmerNotFound() {
        when(propertyRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(request.getFarmerId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }

    @Test
    void testUpdateAddressNotFound() {
        when(propertyRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(request.getFarmerId())).thenReturn(Optional.of(farmer));
        when(addressRepository.findById(request.getAddressId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Address not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(propertyRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        PropertyResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(propertyRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("Property not found"));
    }

    @Test
    void testFindAll() {
        List<Property> list = Arrays.asList(entity);
        when(propertyRepository.findAll()).thenReturn(list);

        List<PropertyResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByFarmer() {
        List<Property> list = Arrays.asList(entity);
        when(propertyRepository.findByFarmerId(farmer.getId())).thenReturn(list);

        List<PropertyResponse> responses = service.findByFarmer(farmer.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(propertyRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(propertyRepository).delete(entity);

        service.remove(id);

        verify(propertyRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(propertyRepository.findById(321L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(321L));
        assertTrue(ex.getMessage().contains("Property not found"));
    }
}

