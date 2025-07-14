package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.NeighborhoodRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.NeighborhoodResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.NeighborhoodFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.NeighborhoodMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.NeighborhoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NeighborhoodService {

	private final NeighborhoodRepository neighborhoodRepository;

	public NeighborhoodService(NeighborhoodRepository neighborhoodRepository) {
		this.neighborhoodRepository = neighborhoodRepository;
	}

	@Transactional
	public NeighborhoodResponse create(NeighborhoodRequest neighborhood) {
		Neighborhood entity = neighborhoodRepository.save(
				NeighborhoodFactory.fromRequest(neighborhood)
		);
		return NeighborhoodMapper.toResponse(entity);
	}

	@Transactional
	public NeighborhoodResponse update(NeighborhoodRequest neighborhood) {
		Neighborhood entity = neighborhoodRepository.save(
				NeighborhoodFactory.fromRequest(neighborhood)
		);
		return NeighborhoodMapper.toResponse(entity);
	}

	public Optional<NeighborhoodResponse> findById(Long id) {
		return neighborhoodRepository.findById(id)
				.map(NeighborhoodMapper::toResponse);
	}

	public List<NeighborhoodResponse> findAll() {
		return NeighborhoodMapper.toListResponse(
				neighborhoodRepository.findAll()
		);
	}

	public List<NeighborhoodResponse> findByMunicipioNome(String municipio) {
		return NeighborhoodMapper.toListResponse(
				neighborhoodRepository.findByCityNome(municipio)
		);
	}

	@Transactional
	public void remove(Neighborhood neighborhood) {
		neighborhoodRepository.delete(neighborhood);
	}
}
