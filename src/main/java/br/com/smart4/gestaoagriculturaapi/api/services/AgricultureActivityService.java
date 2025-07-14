package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AgricultureActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AgricultureActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.AgricultureActivityFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.AgricultureActivityMapper;
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
	public AgricultureActivityResponse create(AgricultureActivityRequest request) {
		AgricultureActivity saved = atividadeAgriculaRepository.save(
				AgricultureActivityFactory.fromRequest(request)
		);
		return AgricultureActivityMapper.toResponse(saved);
	}

	@Transactional
	public AgricultureActivityResponse update(AgricultureActivityRequest request) {
		AgricultureActivity updated = atividadeAgriculaRepository.save(
				AgricultureActivityFactory.fromRequest(request)
		);
		return AgricultureActivityMapper.toResponse(updated);
	}

	public Optional<AgricultureActivityResponse> findById(Long id) {
		return atividadeAgriculaRepository.findById(id)
				.map(AgricultureActivityMapper::toResponse);
	}

	public List<AgricultureActivityResponse> findAll() {
		return AgricultureActivityMapper.toListResponse(
				atividadeAgriculaRepository.findAll()
		);
	}

	public List<AgricultureActivityResponse> findByProperty(Long id) {
		return AgricultureActivityMapper.toListResponse(
				atividadeAgriculaRepository.findByPropertyId(id)
		);
	}

	@Transactional
	public void remove(AgricultureActivity atividadeAgricula) {
		atividadeAgriculaRepository.delete(atividadeAgricula);
	}
}
