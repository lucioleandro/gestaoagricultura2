package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PropertyRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.PropertyFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
	
	private final PropertyRepository propertyRepository;

	public PropertyService(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	@Transactional
	public Property create(PropertyRequest property) {
		return propertyRepository.save(PropertyFactory.fromRequest(property));
	}

	@Transactional
	public Property atualiza(PropertyRequest property) {
		return propertyRepository.save(PropertyFactory.fromRequest(property));
	}
	
	public Optional<Property> findById(Long id) {
		return propertyRepository.findById(id);
	}
	
	public List<Property> findAll() {
		return propertyRepository.findAll();
	}

	public List<Property> findByFarmer(Long id) {
		return propertyRepository.findByFarmerId(id);
	}

	@Transactional
	public void remove(Property property) {
		propertyRepository.delete(property);
	}
	
}
