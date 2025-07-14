package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityFarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityFarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.exceptions.BusinessException;
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

	private final EconomicActivityFarmerRepository repository;
	private final FarmerRepository farmerRepository;
	private final PropertyRepository propertyRepository;
	private final EconomicActivityRepository economicActivityRepository;

	public EconomicActivityFarmerService(EconomicActivityFarmerRepository repository,
										 FarmerRepository farmerRepository,
										 PropertyRepository propertyRepository,
										 EconomicActivityRepository economicActivityRepository) {
		this.repository = repository;
		this.farmerRepository = farmerRepository;
		this.propertyRepository = propertyRepository;
		this.economicActivityRepository = economicActivityRepository;
	}

	@Transactional
	public EconomicActivityFarmerResponse create(EconomicActivityFarmerRequest req) {
		if (req.getPrincipal() &&
				repository.existsByPropertyIdAndPrincipalTrue(req.getPropertyId())) {
			throw new BusinessException(
					"Já existe uma atividade principal cadastrada para esta propriedade");
		}

		var entity = EconomicActivityFarmerFactory.fromRequest(req);
		var saved  = repository.save(entity);
		return EconomicActivityFarmerMapper.toResponse(saved);
	}

	@Transactional
	public EconomicActivityFarmerResponse update(Long id, EconomicActivityFarmerRequest req) {
		// 1) fetch existing
		EconomicActivityFarmer existing = repository.findById(id)
				.orElseThrow(() ->
						new EntityNotFoundException("Economic activity farmer not found with id: " + id)
				);

		// 2) business rule: if marking principal, ensure no other principal exists
		if (req.getPrincipal() &&
				repository.existsByPropertyIdAndPrincipalTrueAndIdNot(req.getPropertyId(), id)) {
			throw new BusinessException(
					"Já existe uma atividade principal cadastrada para esta propriedade"
			);
		}

		// 3) load associations
		Farmer farmer = farmerRepository.findById(req.getFarmerId())
				.orElseThrow(() ->
						new EntityNotFoundException("Farmer not found with id: " + req.getFarmerId())
				);
		Property property = propertyRepository.findById(req.getPropertyId())
				.orElseThrow(() ->
						new EntityNotFoundException("Property not found with id: " + req.getPropertyId())
				);
		EconomicActivity activity = economicActivityRepository.findById(req.getEconomicActivityId())
				.orElseThrow(() ->
						new EntityNotFoundException("Economic activity not found with id: " + req.getEconomicActivityId())
				);

		// 4) apply updates
		existing.setDataInicial(req.getDataInicial());
		existing.setDataFinal(req.getDataFinal());
		existing.setPrincipal(req.getPrincipal());
		existing.setFarmer(farmer);
		existing.setProperty(property);
		existing.setEconomicActivity(activity);

		// 5) save & return
		EconomicActivityFarmer saved = repository.save(existing);
		return EconomicActivityFarmerMapper.toResponse(saved);
	}

	public EconomicActivityFarmerResponse findById(Long id) {
		return repository.findById(id)
				.map(EconomicActivityFarmerMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Economic activity farmer not found with id: " + id));
	}

	public List<EconomicActivityFarmerResponse> findAll() {
		return EconomicActivityFarmerMapper.toListResponse(repository.findAll());
	}

	public List<EconomicActivityFarmerResponse> findByFarmer(Long id) {
		return EconomicActivityFarmerMapper.toListResponse(repository.findByFarmerId(id));
	}

	public List<EconomicActivityFarmerResponse> findByProperty(Long id) {
		return EconomicActivityFarmerMapper.toListResponse(repository.findByPropertyId(id));
	}

	@Transactional
	public void remove(Long id) {
		EconomicActivityFarmer existing = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Economic activity farmer not found with id: " + id));

		repository.delete(existing);
	}
}
