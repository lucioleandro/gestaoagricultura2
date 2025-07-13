package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Address;
import br.com.smart4.gestaoagriculturaapi.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public Address create(Address address) {
		return addressRepository.save(address);
	}

	public Address atualiza(Address address) {
		return addressRepository.save(address);
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
