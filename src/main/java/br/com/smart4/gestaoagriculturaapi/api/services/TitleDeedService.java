package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.TitleDeedRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.TitleDeedFactory;
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
	public TitleDeed create(TitleDeedRequest documentosProperty) throws IOException {
		return titleDeedRepository.save(TitleDeedFactory.fromRequest(documentosProperty));
	}

	@Transactional
	public TitleDeed atualiza(TitleDeedRequest documentosProperty) throws IOException {
		return titleDeedRepository.save(TitleDeedFactory.fromRequest(documentosProperty));
	}

	public Optional<TitleDeed> findById(Long id) {
		return titleDeedRepository.findById(id);
	}

	public List<TitleDeed> findAll() {
		return titleDeedRepository.findAll();
	}

	public List<TitleDeed> findByProperty(Long id) {
		return titleDeedRepository.findByPropertyId(id);
	}

	@Transactional
	public void remove(TitleDeed documentosProperty) {
		titleDeedRepository.delete(documentosProperty);
	}
	
}
