package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.LivestockActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.LivestockActivityFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.LivestockActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivestockActivityService {
	
	private final LivestockActivityRepository livestockActivityRepository;

	public LivestockActivityService(LivestockActivityRepository livestockActivityRepository) {
		this.livestockActivityRepository = livestockActivityRepository;
	}

	public LivestockActivity create(LivestockActivityRequest livestockActivity) {
		return livestockActivityRepository.save(LivestockActivityFactory.fromRequest(livestockActivity));
	}

	public LivestockActivity atualiza(LivestockActivityRequest livestockActivity) {
		return livestockActivityRepository.save(LivestockActivityFactory.fromRequest(livestockActivity));
	}

	public Optional<LivestockActivity> findById(Long id) {
		return livestockActivityRepository.findById(id);
	}

	public List<LivestockActivity> findAll() {
		return livestockActivityRepository.findAll();
	}

	public List<LivestockActivity> findByPropertyId(Long id) {
		return livestockActivityRepository.findByPropertyId(id);
	}

	public void remove(LivestockActivity livestockActivity) {
		livestockActivityRepository.delete(livestockActivity);
	}
	
}
