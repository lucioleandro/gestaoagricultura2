package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.RespostaPadrao;
import br.com.smart4.gestaoagriculturaapi.api.repository.RespostaPadraoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespostaPadraoService {

	@Autowired
	private RespostaPadraoRepository respostaPadraoRepository;
	
	public RespostaPadrao create(RespostaPadrao respostaPadrao) {
		return respostaPadraoRepository.save(respostaPadrao);
	}
	
	public RespostaPadrao atualiza(RespostaPadrao respostaPadrao) {
		return respostaPadraoRepository.save(respostaPadrao);
	}
	
	public Optional<RespostaPadrao> findById(Long id) {
		return respostaPadraoRepository.findById(id);
	}
	
	public List<RespostaPadrao> findAll() {
		return respostaPadraoRepository.findAll();
	}

	public List<RespostaPadrao> findByPerguntaId(Long id) {
		return respostaPadraoRepository.findByPerguntaId(id);
	}
	
	public void remove(RespostaPadrao respostaPadrao) {
		respostaPadraoRepository.delete(respostaPadrao);
	}
	
}
