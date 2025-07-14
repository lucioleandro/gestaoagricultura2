package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.LivestockActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.LivestockActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.LivestockActivityFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.LivestockActivityMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.LivestockActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LivestockActivityService {

	private final LivestockActivityRepository livestockActivityRepository;

	public LivestockActivityService(LivestockActivityRepository livestockActivityRepository) {
		this.livestockActivityRepository = livestockActivityRepository;
	}

	@Transactional
	public LivestockActivityResponse create(LivestockActivityRequest livestockActivity) {
		LivestockActivity entity = livestockActivityRepository.save(
				LivestockActivityFactory.fromRequest(livestockActivity)
		);
		return LivestockActivityMapper.toResponse(entity);
	}

	@Transactional
	public LivestockActivityResponse update(LivestockActivityRequest livestockActivity) {
		LivestockActivity entity = livestockActivityRepository.save(
				LivestockActivityFactory.fromRequest(livestockActivity)
		);
		return LivestockActivityMapper.toResponse(entity);
	}

	public Optional<LivestockActivityResponse> findById(Long id) {
		return livestockActivityRepository.findById(id)
				.map(LivestockActivityMapper::toResponse);
	}

	public List<LivestockActivityResponse> findAll() {
		return LivestockActivityMapper.toListResponse(
				livestockActivityRepository.findAll()
		);
	}

	public List<LivestockActivityResponse> findByPropertyId(Long id) {
		return LivestockActivityMapper.toListResponse(
				livestockActivityRepository.findByPropertyId(id)
		);
	}

	@Transactional
	public void remove(LivestockActivity livestockActivity) {
		livestockActivityRepository.delete(livestockActivity);
	}
}
