package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Property;
import br.com.smart4.gestaoagriculturaapi.api.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	public Property create(Property property) {
		return propertyRepository.save(property);
	}
	
	public Property atualiza(Property property) {
		return propertyRepository.save(property);
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
	
	public void remove(Property property) {
		propertyRepository.delete(property);
	}
	
}
