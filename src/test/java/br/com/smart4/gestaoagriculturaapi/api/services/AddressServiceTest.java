package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AddressResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AddressRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.CityRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.NeighborhoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @InjectMocks
    private AddressService addressService;

    private AddressRequest request;
    private Address address;
    private AddressResponse expectedResponse;

    @BeforeEach
    void setUp() {
        request = new AddressRequest();
        request.setLogradouro("Rua A");
        request.setNumero("123");
        request.setCityId(1L);
        request.setNeighborhoodId(2L);
        request.setCep("12345-678");

        address = new Address();
        address.setId(10L);
        address.setLogradouro(request.getLogradouro());
        address.setNumero(request.getNumero());
        City city = new City(); city.setId(request.getCityId());
        address.setCity(city);
        Neighborhood neighborhood = new Neighborhood(); neighborhood.setId(request.getNeighborhoodId());
        address.setNeighborhood(neighborhood);
        address.setCep(request.getCep());

        expectedResponse = new AddressResponse();
        expectedResponse.setId(address.getId());
        expectedResponse.setLogradouro(address.getLogradouro());
        expectedResponse.setNumero(address.getNumero());
        expectedResponse.setCityId(address.getCity().getId());
        expectedResponse.setNeighborhoodId(address.getNeighborhood().getId());
        expectedResponse.setCep(address.getCep());
    }

    @Test
    void testCreate() {
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        AddressResponse result = addressService.create(request);

        assertNotNull(result);
        assertEquals(expectedResponse.getId(), result.getId());
        assertEquals(expectedResponse.getLogradouro(), result.getLogradouro());
        assertEquals(expectedResponse.getNumero(), result.getNumero());
        assertEquals(expectedResponse.getCityId(), result.getCityId());
        assertEquals(expectedResponse.getNeighborhoodId(), result.getNeighborhoodId());
        assertEquals(expectedResponse.getCep(), result.getCep());

        ArgumentCaptor<Address> captor = ArgumentCaptor.forClass(Address.class);
        verify(addressRepository).save(captor.capture());
        Address savedArg = captor.getValue();
        assertEquals(request.getLogradouro(), savedArg.getLogradouro());
        assertEquals(request.getNumero(), savedArg.getNumero());
        assertEquals(request.getCep(), savedArg.getCep());
        assertNull(savedArg.getId());
    }

    @Test
    void testUpdateSuccess() {
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));

        City newCity = new City(); newCity.setId(3L);
        when(cityRepository.findById(3L)).thenReturn(Optional.of(newCity));

        Neighborhood newNeighborhood = new Neighborhood(); newNeighborhood.setId(4L);
        when(neighborhoodRepository.findById(4L)).thenReturn(Optional.of(newNeighborhood));

        AddressRequest updateRequest = new AddressRequest();
        updateRequest.setLogradouro("Rua B");
        updateRequest.setNumero("456");
        updateRequest.setCityId(3L);
        updateRequest.setNeighborhoodId(4L);
        updateRequest.setCep("87654-321");

        Address updatedAddress = new Address();
        updatedAddress.setId(address.getId());
        updatedAddress.setLogradouro(updateRequest.getLogradouro());
        updatedAddress.setNumero(updateRequest.getNumero());
        updatedAddress.setCity(newCity);
        updatedAddress.setNeighborhood(newNeighborhood);
        updatedAddress.setCep(updateRequest.getCep());

        when(addressRepository.save(any(Address.class))).thenReturn(updatedAddress);

        AddressResponse result = addressService.update(address.getId(), updateRequest);

        assertEquals(updateRequest.getLogradouro(), result.getLogradouro());
        assertEquals(updateRequest.getNumero(), result.getNumero());
        assertEquals(updateRequest.getCityId(), result.getCityId());
        assertEquals(updateRequest.getNeighborhoodId(), result.getNeighborhoodId());
        assertEquals(updateRequest.getCep(), result.getCep());

        verify(addressRepository).findById(address.getId());
        verify(cityRepository).findById(3L);
        verify(neighborhoodRepository).findById(4L);
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void testUpdateNotFound() {
        when(addressRepository.findById(999L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> addressService.update(999L, request));
        assertTrue(ex.getMessage().contains("Address not found"));
    }

    @Test
    void testUpdateCityNotFound() {
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        when(cityRepository.findById(request.getCityId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> addressService.update(address.getId(), request));
        assertTrue(ex.getMessage().contains("City not found"));
    }

    @Test
    void testUpdateNeighborhoodNotFound() {
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        when(cityRepository.findById(request.getCityId())).thenReturn(Optional.of(address.getCity()));
        when(neighborhoodRepository.findById(request.getNeighborhoodId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> addressService.update(address.getId(), request));
        assertTrue(ex.getMessage().contains("Neighborhood not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        AddressResponse result = addressService.findById(address.getId());
        assertEquals(address.getId(), result.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(addressRepository.findById(888L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> addressService.findById(888L));
        assertTrue(ex.getMessage().contains("Address not found"));
    }

    @Test
    void testFindAll() {
        List<Address> list = Arrays.asList(address);
        when(addressRepository.findAll()).thenReturn(list);

        List<AddressResponse> responses = addressService.findAll();
        assertEquals(1, responses.size());
        assertEquals(address.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        doNothing().when(addressRepository).delete(address);

        addressService.remove(address.getId());

        verify(addressRepository).delete(address);
    }

    @Test
    void testRemoveNotFound() {
        when(addressRepository.findById(777L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> addressService.remove(777L));
        assertTrue(ex.getMessage().contains("Address not found"));
    }
}

