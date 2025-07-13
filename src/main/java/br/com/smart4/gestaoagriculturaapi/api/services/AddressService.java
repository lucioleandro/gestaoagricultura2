package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.AddressFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public Address create(AddressRequest address) {
		return addressRepository.save(AddressFactory.fromRequest(address));
	}

	public Address atualiza(AddressRequest address) {
		return addressRepository.save(AddressFactory.fromRequest(address));
	}

	public Optional<Address> findById(Long id) {
		return addressRepository.findById(id);
	}

	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	public void remove(Address address) {
		addressRepository.delete(address);
	}
	
}
