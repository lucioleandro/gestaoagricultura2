package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.CityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.CityResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.CityFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.CityMapper;
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
	public CityResponse create(CityRequest municipio) {
		City saved = municipioRepository.save(CityFactory.fromRequest(municipio));
		return CityMapper.toResponse(saved);
	}

	@Transactional
	public CityResponse update(CityRequest municipio) {
		City updated = municipioRepository.save(CityFactory.fromRequest(municipio));
		return CityMapper.toResponse(updated);
	}

	public Optional<CityResponse> findById(Long id) {
		return municipioRepository.findById(id)
				.map(CityMapper::toResponse);
	}

	public List<CityResponse> findAll() {
		return CityMapper.toListResponse(municipioRepository.findAll());
	}

	@Transactional
	public void remove(City municipio) {
		municipioRepository.delete(municipio);
	}
}
