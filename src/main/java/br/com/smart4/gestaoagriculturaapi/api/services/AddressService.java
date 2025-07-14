package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AddressResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.AddressFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.AddressMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AddressRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.CityRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.NeighborhoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    public AddressService(AddressRepository addressRepository, CityRepository cityRepository, NeighborhoodRepository neighborhoodRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Transactional
    public AddressResponse create(AddressRequest request) {
        Address saved = addressRepository.save(AddressFactory.fromRequest(request));
        return AddressMapper.toResponse(saved);
    }

    @Transactional
    public AddressResponse update(Long id, AddressRequest request) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));

        var city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + request.getCityId()));

        var neighborhood = neighborhoodRepository.findById(request.getNeighborhoodId())
                .orElseThrow(() -> new EntityNotFoundException("Neighborhood not found with id: " + request.getNeighborhoodId()));

        existing.setLogradouro(request.getLogradouro());
        existing.setNumero(request.getNumero());
        existing.setCity(city);
        existing.setNeighborhood(neighborhood);
        existing.setCep(request.getCep());

        Address updated = addressRepository.save(existing);
        return AddressMapper.toResponse(updated);
    }


    public AddressResponse findById(Long id) {
        return addressRepository.findById(id)
                .map(AddressMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
    }

    public List<AddressResponse> findAll() {
        return AddressMapper.toListResponse(addressRepository.findAll());
    }

    @Transactional
    public void remove(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
        addressRepository.delete(address);
    }
}
