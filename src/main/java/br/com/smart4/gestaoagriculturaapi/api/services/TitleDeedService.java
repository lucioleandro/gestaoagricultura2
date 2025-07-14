package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.TitleDeedRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.TitleDeedResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.TitleDeedFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.TitleDeedMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.TitleDeedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TitleDeedService {

	private final TitleDeedRepository titleDeedRepository;

	public TitleDeedService(TitleDeedRepository titleDeedRepository) {
		this.titleDeedRepository = titleDeedRepository;
	}

	@Transactional
	public TitleDeedResponse create(TitleDeedRequest documentosProperty) throws IOException {
		TitleDeed entity = titleDeedRepository.save(
				TitleDeedFactory.fromRequest(documentosProperty)
		);
		return TitleDeedMapper.toResponse(entity);
	}

	@Transactional
	public TitleDeedResponse update(TitleDeedRequest documentosProperty) throws IOException {
		TitleDeed entity = titleDeedRepository.save(
				TitleDeedFactory.fromRequest(documentosProperty)
		);
		return TitleDeedMapper.toResponse(entity);
	}

	public Optional<TitleDeedResponse> findById(Long id) {
		return titleDeedRepository.findById(id)
				.map(TitleDeedMapper::toResponse);
	}

	public List<TitleDeedResponse> findAll() {
		return TitleDeedMapper.toListResponse(
				titleDeedRepository.findAll()
		);
	}

	public List<TitleDeedResponse> findByProperty(Long id) {
		return TitleDeedMapper.toListResponse(
				titleDeedRepository.findByPropertyId(id)
		);
	}

	@Transactional
	public void remove(TitleDeed documentosProperty) {
		titleDeedRepository.delete(documentosProperty);
	}
}
