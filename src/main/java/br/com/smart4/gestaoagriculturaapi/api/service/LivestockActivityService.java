package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.repository.LivestockActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivestockActivityService {
	
	@Autowired
	private LivestockActivityRepository livestockActivityRepository;
	
	public LivestockActivity create(LivestockActivity livestockActivity) {
		return livestockActivityRepository.save(livestockActivity);
	}

	public LivestockActivity atualiza(LivestockActivity livestockActivity) {
		return livestockActivityRepository.save(livestockActivity);
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
