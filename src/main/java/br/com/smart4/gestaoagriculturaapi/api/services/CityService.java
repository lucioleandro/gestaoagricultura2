package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.CityRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.CityFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
	
	private final CityRepository municipioRepository;

	public CityService(CityRepository municipioRepository) {
		this.municipioRepository = municipioRepository;
	}

	@Transactional
	public City create(CityRequest municipio) {
		return municipioRepository.save(CityFactory.fromRequest(municipio));
	}

	@Transactional
	public City atualiza(CityRequest municipio) {
		return municipioRepository.save(CityFactory.fromRequest(municipio));
	}

	public Optional<City> findById(Long id) {
		return municipioRepository.findById(id);
	}

	public List<City> findAll() {
		return municipioRepository.findAll();
	}

	@Transactional
	public void remove(City municipio) {
		municipioRepository.delete(municipio);
	}
	
}
