package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PropertyRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PropertyResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.PropertyFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.PropertyMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AddressRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropertyService {

	private final PropertyRepository propertyRepository;
	private final FarmerRepository farmerRepository;
	private final AddressRepository addressRepository;

	public PropertyService(PropertyRepository propertyRepository,
						   FarmerRepository farmerRepository,
						   AddressRepository addressRepository) {
		this.propertyRepository = propertyRepository;
		this.farmerRepository = farmerRepository;
		this.addressRepository = addressRepository;
	}

	@Transactional
	public PropertyResponse create(PropertyRequest request) {
		Property entity = propertyRepository.save(PropertyFactory.fromRequest(request));
		return PropertyMapper.toResponse(entity);
	}

	@Transactional
	public PropertyResponse update(Long id, PropertyRequest request) {
		Property existing = propertyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));

		Farmer farmer = farmerRepository.findById(request.getFarmerId())
				.orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + request.getFarmerId()));

		Address address = addressRepository.findById(request.getAddressId())
				.orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + request.getAddressId()));

		existing.setNome(request.getNome());
		existing.setItr(request.getItr());
		existing.setIncra(request.getIncra());
		existing.setLatitude(request.getLatitude());
		existing.setLongitude(request.getLongitude());
		existing.setAreaTotal(request.getAreaTotal());
		existing.setAreaAgricola(request.getAreaAgricola());
		existing.setReservaLegal(request.getReservaLegal());
		existing.setTipoResidencia(request.getTipoResidencia());
		existing.setRegularizacaoFundiaria(request.getRegularizacaoFundiaria());
		existing.setFarmer(farmer);
		existing.setAddress(address);

		Property updated = propertyRepository.save(existing);
		return PropertyMapper.toResponse(updated);
	}

	public PropertyResponse findById(Long id) {
		return propertyRepository.findById(id)
				.map(PropertyMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
	}

	public List<PropertyResponse> findAll() {
		return PropertyMapper.toListResponse(propertyRepository.findAll());
	}

	public List<PropertyResponse> findByFarmer(Long farmerId) {
		return PropertyMapper.toListResponse(propertyRepository.findByFarmerId(farmerId));
	}

	@Transactional
	public void remove(Long id) {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
		propertyRepository.delete(property);
	}
}
