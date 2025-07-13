package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.repository.AgricultureActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgricultureActivityService {
	
	@Autowired
	private AgricultureActivityRepository atividadeAgriculaRepository;

	public AgricultureActivity create(AgricultureActivity atividadeAgricula) {
		return atividadeAgriculaRepository.save(atividadeAgricula);
	}

	public void atualiza(AgricultureActivity atividadeAgricula) {
		atividadeAgriculaRepository.save(atividadeAgricula);
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
	
	public void remove(AgricultureActivity atividadeAgricula) {
		atividadeAgriculaRepository.delete(atividadeAgricula);
	}
	
}
