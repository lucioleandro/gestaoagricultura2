package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.NeighborhoodRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.NeighborhoodFactory;
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
	public Neighborhood create(NeighborhoodRequest neighborhood) {
		return neighborhoodRepository.save(NeighborhoodFactory.fromRequest(neighborhood));
	}

	@Transactional
	public Neighborhood atualiza(NeighborhoodRequest neighborhood) {
		return neighborhoodRepository.save(NeighborhoodFactory.fromRequest(neighborhood));
	}

	public Optional<Neighborhood> findById(Long id) {
		return neighborhoodRepository.findById(id);
	}

	public List<Neighborhood> findAll() {
		return neighborhoodRepository.findAll();
	}

	public List<Neighborhood> findByMunicipioNome(String municipio) {
		return neighborhoodRepository.findByCityNome(municipio);
	}

	@Transactional
	public void remove(Neighborhood neighborhood) {
		neighborhoodRepository.delete(neighborhood);
	}
	
}
