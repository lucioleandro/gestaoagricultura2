package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.CityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.CityResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.CityFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.CityMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

	private final CityRepository cityRepository;

	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Transactional
	public CityResponse create(CityRequest request) {
		City saved = cityRepository.save(CityFactory.fromRequest(request));
		return CityMapper.toResponse(saved);
	}

	@Transactional
	public CityResponse update(Long id, CityRequest request) {
		City existing = cityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));

		existing.setNome(request.getNome());
		existing.setUf(request.getUf());
		existing.setCadastroUnico(request.getCadastroUnico());

		City updated = cityRepository.save(existing);
		return CityMapper.toResponse(updated);
	}

	public CityResponse findById(Long id) {
		return cityRepository.findById(id)
				.map(CityMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));
	}

	public List<CityResponse> findAll() {
		return CityMapper.toListResponse(cityRepository.findAll());
	}

	@Transactional
	public void remove(Long id) {
		City existing = cityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));

		cityRepository.delete(existing);
	}
}
