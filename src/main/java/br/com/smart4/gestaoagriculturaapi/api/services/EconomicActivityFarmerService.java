package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityFarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityFarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.EconomicActivityFarmerFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.EconomicActivityFarmerMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityFarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EconomicActivityFarmerService {

	private final EconomicActivityFarmerRepository economicActivityRepository;
	private final FarmerRepository farmerRepository;
	private final PropertyRepository propertyRepository;
	private final EconomicActivityRepository economicActivityRepo;

	public EconomicActivityFarmerService(EconomicActivityFarmerRepository economicActivityRepository,
										 FarmerRepository farmerRepository,
										 PropertyRepository propertyRepository,
										 EconomicActivityRepository economicActivityRepo) {
		this.economicActivityRepository = economicActivityRepository;
		this.farmerRepository = farmerRepository;
		this.propertyRepository = propertyRepository;
		this.economicActivityRepo = economicActivityRepo;
	}

	@Transactional
	public EconomicActivityFarmerResponse create(EconomicActivityFarmerRequest request) {
		EconomicActivityFarmer entity = economicActivityRepository.save(EconomicActivityFarmerFactory.fromRequest(request));
		return EconomicActivityFarmerMapper.toResponse(entity);
	}

	@Transactional
	public EconomicActivityFarmerResponse update(Long id, EconomicActivityFarmerRequest request) {
		EconomicActivityFarmer existing = economicActivityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Economic activity farmer not found with id: " + id));

		Farmer farmer = farmerRepository.findById(request.getFarmerId())
				.orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + request.getFarmerId()));

		Property property = propertyRepository.findById(request.getPropertyId())
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + request.getPropertyId()));

		EconomicActivity economicActivity = economicActivityRepo.findById(request.getEconomicActivityId())
				.orElseThrow(() -> new EntityNotFoundException("Economic activity not found with id: " + request.getEconomicActivityId()));

		existing.setDataInicial(request.getDataInicial());
		existing.setDataFinal(request.getDataFinal());
		existing.setPrincipal(request.getPrincipal());
		existing.setFarmer(farmer);
		existing.setProperty(property);
		existing.setEconomicActivity(economicActivity);

		EconomicActivityFarmer updated = economicActivityRepository.save(existing);
		return EconomicActivityFarmerMapper.toResponse(updated);
	}

	public EconomicActivityFarmerResponse findById(Long id) {
		return economicActivityRepository.findById(id)
				.map(EconomicActivityFarmerMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Economic activity farmer not found with id: " + id));
	}

	public List<EconomicActivityFarmerResponse> findAll() {
		return EconomicActivityFarmerMapper.toListResponse(economicActivityRepository.findAll());
	}

	public List<EconomicActivityFarmerResponse> findByFarmer(Long id) {
		return EconomicActivityFarmerMapper.toListResponse(economicActivityRepository.findByFarmerId(id));
	}

	public List<EconomicActivityFarmerResponse> findByProperty(Long id) {
		return EconomicActivityFarmerMapper.toListResponse(economicActivityRepository.findByPropertyId(id));
	}

	@Transactional
	public void remove(Long id) {
		EconomicActivityFarmer existing = economicActivityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Economic activity farmer not found with id: " + id));

		economicActivityRepository.delete(existing);
	}
}
