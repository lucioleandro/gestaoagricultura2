package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.EconomicActivityFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.EconomicActivityMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EconomicActivityService {

	private final EconomicActivityRepository economicActivityRepository;

	public EconomicActivityService(EconomicActivityRepository economicActivityRepository) {
		this.economicActivityRepository = economicActivityRepository;
	}

	@Transactional
	public EconomicActivityResponse create(EconomicActivityRequest request) {
		EconomicActivity entity = economicActivityRepository.save(EconomicActivityFactory.fromRequest(request));
		return EconomicActivityMapper.toResponse(entity);
	}

	@Transactional
	public EconomicActivityResponse update(EconomicActivityRequest request) {
		EconomicActivity entity = economicActivityRepository.save(EconomicActivityFactory.fromRequest(request));
		return EconomicActivityMapper.toResponse(entity);
	}

	public Optional<EconomicActivityResponse> findById(Long id) {
		return economicActivityRepository.findById(id)
				.map(EconomicActivityMapper::toResponse);
	}

	public List<EconomicActivityResponse> findAll() {
		return EconomicActivityMapper.toListResponse(economicActivityRepository.findAll());
	}

	@Transactional
	public void remove(EconomicActivity economicActivity) {
		economicActivityRepository.delete(economicActivity);
	}
}
