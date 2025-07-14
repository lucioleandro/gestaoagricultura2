package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityFarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityFarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.EconomicActivityFarmerFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.EconomicActivityFarmerMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityFarmerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EconomicActivityFarmerService {

	private final EconomicActivityFarmerRepository economicActivityRepository;

	public EconomicActivityFarmerService(EconomicActivityFarmerRepository economicActivityRepository) {
		this.economicActivityRepository = economicActivityRepository;
	}

	@Transactional
	public EconomicActivityFarmerResponse create(EconomicActivityFarmerRequest request) {
		EconomicActivityFarmer entity = economicActivityRepository.save(EconomicActivityFarmerFactory.fromRequest(request));
		return EconomicActivityFarmerMapper.toResponse(entity);
	}

	@Transactional
	public EconomicActivityFarmerResponse update(EconomicActivityFarmerRequest request) {
		EconomicActivityFarmer entity = economicActivityRepository.save(EconomicActivityFarmerFactory.fromRequest(request));
		return EconomicActivityFarmerMapper.toResponse(entity);
	}

	public Optional<EconomicActivityFarmerResponse> findById(Long id) {
		return economicActivityRepository.findById(id)
				.map(EconomicActivityFarmerMapper::toResponse);
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
	public void remove(EconomicActivityFarmer entity) {
		economicActivityRepository.delete(entity);
	}
}
