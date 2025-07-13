package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.repository.TitleDeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentosPropertyService {

	@Autowired
	private TitleDeedRepository documentosPropertyRepository;
	
	public TitleDeed create(TitleDeed documentosProperty) {
		return documentosPropertyRepository.save(documentosProperty);
	}

	public TitleDeed atualiza(TitleDeed documentosProperty) {
		return documentosPropertyRepository.save(documentosProperty);
	}

	public Optional<TitleDeed> findById(Long id) {
		return documentosPropertyRepository.findById(id);
	}

	public List<TitleDeed> findAll() {
		return documentosPropertyRepository.findAll();
	}

	public List<TitleDeed> findByProperty(Long id) {
		return documentosPropertyRepository.findByPropertyId(id);
	}

	public void remove(TitleDeed documentosProperty) {
		documentosPropertyRepository.delete(documentosProperty);
	}
	
}
