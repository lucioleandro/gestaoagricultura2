package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AddressResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.AddressFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.AddressMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public AddressResponse create(AddressRequest request) {
        Address saved = addressRepository.save(AddressFactory.fromRequest(request));
        return AddressMapper.toResponse(saved);
    }

    @Transactional
    public AddressResponse update(AddressRequest request) {
        Address updated = addressRepository.save(AddressFactory.fromRequest(request));
        return AddressMapper.toResponse(updated);
    }

    public Optional<AddressResponse> findById(Long id) {
        return addressRepository.findById(id)
                .map(AddressMapper::toResponse);
    }

    public List<AddressResponse> findAll() {
        return AddressMapper.toListResponse(addressRepository.findAll());
    }

    @Transactional
    public void remove(Address address) {
        addressRepository.delete(address);
    }
}
