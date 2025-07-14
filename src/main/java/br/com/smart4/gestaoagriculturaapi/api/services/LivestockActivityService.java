package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.LivestockActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.LivestockActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.LivestockActivityFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.LivestockActivityMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.LivestockActivityRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivestockActivityService {

	private final LivestockActivityRepository livestockActivityRepository;
	private final PropertyRepository propertyRepository;

	public LivestockActivityService(LivestockActivityRepository livestockActivityRepository, PropertyRepository propertyRepository) {
		this.livestockActivityRepository = livestockActivityRepository;
        this.propertyRepository = propertyRepository;
    }

	@Transactional
	public LivestockActivityResponse create(LivestockActivityRequest request) {
		LivestockActivity entity = livestockActivityRepository.save(
				LivestockActivityFactory.fromRequest(request)
		);
		return LivestockActivityMapper.toResponse(entity);
	}

	@Transactional
	public LivestockActivityResponse update(Long id, LivestockActivityRequest request) {
		LivestockActivity existing = livestockActivityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Livestock activity not found with id: " + id));

		Property property = propertyRepository.findById(request.getPropertyId())
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + request.getPropertyId()));

		existing.setEspecie(request.getEspecie());
		existing.setQuantidade(request.getQuantidade());
		existing.setRaca(request.getRaca());
		existing.setProperty(property);

		LivestockActivity updated = livestockActivityRepository.save(existing);
		return LivestockActivityMapper.toResponse(updated);
	}

	public LivestockActivityResponse findById(Long id) {
		return livestockActivityRepository.findById(id)
				.map(LivestockActivityMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Livestock activity not found with id: " + id));
	}

	public List<LivestockActivityResponse> findAll() {
		return LivestockActivityMapper.toListResponse(
				livestockActivityRepository.findAll()
		);
	}

	public List<LivestockActivityResponse> findByPropertyId(Long propertyId) {
		return LivestockActivityMapper.toListResponse(
				livestockActivityRepository.findByPropertyId(propertyId)
		);
	}

	@Transactional
	public void remove(Long id) {
		LivestockActivity existing = livestockActivityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Livestock activity not found with id: " + id));

		livestockActivityRepository.delete(existing);
	}
}
