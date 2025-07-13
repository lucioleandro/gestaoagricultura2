package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.repository.EconomicActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EconomicActivityService {
	
	@Autowired
	private EconomicActivityRepository economicActivityRepository;
	
	public EconomicActivity create(EconomicActivity economicActivity) {
		return economicActivityRepository.save(economicActivity);
	}

	public EconomicActivity atualiza(EconomicActivity economicActivity) {
		return economicActivityRepository.save(economicActivity);
	}

	public Optional<EconomicActivity> findById(Long id) {
		return economicActivityRepository.findById(id);
	}

	public List<EconomicActivity> findAll() {
		return economicActivityRepository.findAll();
	}

	public void remove(EconomicActivity economicActivity) {
		economicActivityRepository.delete(economicActivity);
	}
	
}
