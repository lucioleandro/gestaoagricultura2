package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.NeighborhoodRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.NeighborhoodResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.NeighborhoodFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.NeighborhoodMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.CityRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.NeighborhoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NeighborhoodService {

	private final NeighborhoodRepository neighborhoodRepository;
	private final CityRepository cityRepository;

	public NeighborhoodService(NeighborhoodRepository neighborhoodRepository, CityRepository cityRepository) {
		this.neighborhoodRepository = neighborhoodRepository;
        this.cityRepository = cityRepository;
    }

	@Transactional
	public NeighborhoodResponse create(NeighborhoodRequest request) {
		Neighborhood entity = neighborhoodRepository.save(
				NeighborhoodFactory.fromRequest(request)
		);
		return NeighborhoodMapper.toResponse(entity);
	}

	@Transactional
	public NeighborhoodResponse update(Long id, NeighborhoodRequest request) {
		Neighborhood existing = neighborhoodRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Neighborhood not found with id: " + id));

		City city = cityRepository.findById(request.getCityId())
				.orElseThrow(() -> new EntityNotFoundException("City not found with id: " + request.getCityId()));

		existing.setNome(request.getNome());
		existing.setZona(request.getZona());
		existing.setCity(city);

		Neighborhood updated = neighborhoodRepository.save(existing);
		return NeighborhoodMapper.toResponse(updated);
	}

	public NeighborhoodResponse findById(Long id) {
		return neighborhoodRepository.findById(id)
				.map(NeighborhoodMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Neighborhood not found with id: " + id));
	}

	public List<NeighborhoodResponse> findAll() {
		return NeighborhoodMapper.toListResponse(
				neighborhoodRepository.findAll()
		);
	}

	public List<NeighborhoodResponse> findByMunicipioNome(String municipioNome) {
		return NeighborhoodMapper.toListResponse(
				neighborhoodRepository.findByCityNome(municipioNome)
		);
	}

	@Transactional
	public void remove(Long id) {
		Neighborhood existing = neighborhoodRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Neighborhood not found with id: " + id));
		neighborhoodRepository.delete(existing);
	}
}
