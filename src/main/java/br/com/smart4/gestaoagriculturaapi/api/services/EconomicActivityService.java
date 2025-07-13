package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.EconomicActivityFactory;
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
	public EconomicActivity create(EconomicActivityRequest economicActivity) {
		return economicActivityRepository.save(EconomicActivityFactory.fromRequest(economicActivity));
	}

	@Transactional
	public EconomicActivity update(EconomicActivityRequest economicActivity) {
		return economicActivityRepository.save(EconomicActivityFactory.fromRequest(economicActivity));
	}

	public Optional<EconomicActivity> findById(Long id) {
		return economicActivityRepository.findById(id);
	}

	public List<EconomicActivity> findAll() {
		return economicActivityRepository.findAll();
	}

	@Transactional
	public void remove(EconomicActivity economicActivity) {
		economicActivityRepository.delete(economicActivity);
	}
	
}
