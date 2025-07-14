package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.domains.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.TitleDeedRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.TitleDeedResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.TitleDeedFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.TitleDeedMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.TitleDeedRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TitleDeedService {

	private final TitleDeedRepository titleDeedRepository;
	private final PropertyRepository propertyRepository;

	public TitleDeedService(TitleDeedRepository titleDeedRepository, PropertyRepository propertyRepository) {
		this.titleDeedRepository = titleDeedRepository;
		this.propertyRepository = propertyRepository;
	}

	@Transactional
	public TitleDeedResponse create(TitleDeedRequest request) throws IOException {
		propertyRepository.findById(request.getPropertyId())
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + request.getPropertyId()));

		TitleDeed saved = titleDeedRepository.save(
				TitleDeedFactory.fromRequest(request)
		);
		return TitleDeedMapper.toResponse(saved);
	}

	@Transactional
	public TitleDeedResponse update(Long id, TitleDeedRequest request) throws IOException {
		TitleDeed existing = titleDeedRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("TitleDeed not found with id: " + id));

		if (!existing.getProperty().getId().equals(request.getPropertyId())) {
			Property property = propertyRepository.findById(request.getPropertyId())
					.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + request.getPropertyId()));
			existing.setProperty(property);
		}

		existing.setTitulo(request.getTitulo());
		existing.setObservacao(request.getObservacao());
		existing.setDocumento(request.getDocumento());
		existing.setDataAdicao(LocalDateTime.now());
		existing.setBytes(request.getFile().getBytes());
		existing.setExtensao(getExtension(request.getFile().getOriginalFilename()));

		return TitleDeedMapper.toResponse(titleDeedRepository.save(existing));
	}

	public Optional<TitleDeedResponse> findById(Long id) {
		return titleDeedRepository.findById(id)
				.map(TitleDeedMapper::toResponse);
	}

	public List<TitleDeedResponse> findAll() {
		return TitleDeedMapper.toListResponse(titleDeedRepository.findAll());
	}

	public List<TitleDeedResponse> findByProperty(Long id) {
		return TitleDeedMapper.toListResponse(titleDeedRepository.findByPropertyId(id));
	}

	@Transactional
	public void remove(TitleDeed titleDeed) {
		titleDeedRepository.delete(titleDeed);
	}

	private String getExtension(String filename) {
		if (filename == null || !filename.contains(".")) return "";
		return filename.substring(filename.lastIndexOf('.') + 1);
	}
}
