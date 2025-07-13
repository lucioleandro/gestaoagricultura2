package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.repository.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NeighborhoodService {

	@Autowired
	private NeighborhoodRepository neighborhoodRepository;

	public Neighborhood create(Neighborhood neighborhood) {
		return neighborhoodRepository.save(neighborhood);
	}

	public Neighborhood atualiza(Neighborhood neighborhood) {
		return neighborhoodRepository.save(neighborhood);
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
	
	public void remove(Neighborhood neighborhood) {
		neighborhoodRepository.delete(neighborhood);
	}
	
}
