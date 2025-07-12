package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.DocumentoPropriedade;
import br.com.smart4.gestaoagriculturaapi.api.repository.DocumentosPropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentosPropriedadeService {

	@Autowired
	private DocumentosPropriedadeRepository documentosPropriedadeRepository;
	
	public DocumentoPropriedade create(DocumentoPropriedade documentosPropriedade) {
		return documentosPropriedadeRepository.save(documentosPropriedade);
	}

	public DocumentoPropriedade atualiza(DocumentoPropriedade documentosPropriedade) {
		return documentosPropriedadeRepository.save(documentosPropriedade);
	}

	public Optional<DocumentoPropriedade> findById(Long id) {
		return documentosPropriedadeRepository.findById(id);
	}

	public List<DocumentoPropriedade> findAll() {
		return documentosPropriedadeRepository.findAll();
	}

	public List<DocumentoPropriedade> findByPropriedade(Long id) {
		return documentosPropriedadeRepository.findByPropriedadeId(id);
	}

	public void remove(DocumentoPropriedade documentosPropriedade) {
		documentosPropriedadeRepository.delete(documentosPropriedade);
	}
	
}
