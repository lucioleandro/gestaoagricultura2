package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.StandardResponseRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.StandardResponseFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.StandardResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StandardResponseService {

	private final StandardResponseRepository respostaPadraoRepository;

	public StandardResponseService(StandardResponseRepository respostaPadraoRepository) {
		this.respostaPadraoRepository = respostaPadraoRepository;
	}

	@Transactional
	public StandardResponse create(StandardResponseRequest respostaPadrao) {
		return respostaPadraoRepository.save(StandardResponseFactory.fromRequest(respostaPadrao));
	}

	@Transactional
	public StandardResponse update(StandardResponseRequest respostaPadrao) {
		return respostaPadraoRepository.save(StandardResponseFactory.fromRequest(respostaPadrao));
	}
	
	public Optional<StandardResponse> findById(Long id) {
		return respostaPadraoRepository.findById(id);
	}
	
	public List<StandardResponse> findAll() {
		return respostaPadraoRepository.findAll();
	}

	public List<StandardResponse> findByQuestionId(Long id) {
		return respostaPadraoRepository.findByQuestionId(id);
	}

	@Transactional
	public void remove(StandardResponse respostaPadrao) {
		respostaPadraoRepository.delete(respostaPadrao);
	}
	
}
