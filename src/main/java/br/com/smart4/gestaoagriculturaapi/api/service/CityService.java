package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
	
	@Autowired
	private CityRepository municipioRepository;
	
	public br.com.smart4.gestaoagriculturaapi.api.domain.City create(br.com.smart4.gestaoagriculturaapi.api.domain.City municipio) {
		return municipioRepository.save(municipio);
	}

	public br.com.smart4.gestaoagriculturaapi.api.domain.City atualiza(br.com.smart4.gestaoagriculturaapi.api.domain.City municipio) {
		return municipioRepository.save(municipio);
	}

	public Optional<br.com.smart4.gestaoagriculturaapi.api.domain.City> findById(Long id) {
		return municipioRepository.findById(id);
	}

	public List<br.com.smart4.gestaoagriculturaapi.api.domain.City> findAll() {
		return municipioRepository.findAll();
	}
	
	public void remove(br.com.smart4.gestaoagriculturaapi.api.domain.City municipio) {
		municipioRepository.delete(municipio);
	}
	
}
