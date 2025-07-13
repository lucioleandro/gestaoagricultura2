package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AgricultureActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.AgricultureActivityFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AgricultureActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AgricultureActivityService {
	
	private final AgricultureActivityRepository atividadeAgriculaRepository;

	public AgricultureActivityService(AgricultureActivityRepository atividadeAgriculaRepository) {
		this.atividadeAgriculaRepository = atividadeAgriculaRepository;
	}

	@Transactional
	public AgricultureActivity create(AgricultureActivityRequest atividadeAgricula) {
		return atividadeAgriculaRepository.save(AgricultureActivityFactory.fromRequest(atividadeAgricula));
	}

	@Transactional
	public void update(AgricultureActivityRequest atividadeAgricula) {
		atividadeAgriculaRepository.save(AgricultureActivityFactory.fromRequest(atividadeAgricula));
	}

	public Optional<AgricultureActivity> findById(Long id) {
		return atividadeAgriculaRepository.findById(id);
	}

	public List<AgricultureActivity> findAll() {
		return atividadeAgriculaRepository.findAll();
	}

	public List<AgricultureActivity> findByProperty(Long id) {
		return atividadeAgriculaRepository.findByPropertyId(id);
	}

	@Transactional
	public void remove(AgricultureActivity atividadeAgricula) {
		atividadeAgriculaRepository.delete(atividadeAgricula);
	}
	
}
