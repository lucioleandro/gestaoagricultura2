package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.repository.StandardResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StandardResponseService {

	@Autowired
	private StandardResponseRepository respostaPadraoRepository;
	
	public StandardResponse create(StandardResponse respostaPadrao) {
		return respostaPadraoRepository.save(respostaPadrao);
	}
	
	public StandardResponse atualiza(StandardResponse respostaPadrao) {
		return respostaPadraoRepository.save(respostaPadrao);
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
	
	public void remove(StandardResponse respostaPadrao) {
		respostaPadraoRepository.delete(respostaPadrao);
	}
	
}
