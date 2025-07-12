package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.RespostaPergunta;
import br.com.smart4.gestaoagriculturaapi.api.repository.RespostaPerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespostaPerguntaService {

	@Autowired
	private RespostaPerguntaRepository respostaPerguntaRepository;
	
	public RespostaPergunta create(RespostaPergunta respostaPergunta) {
		return respostaPerguntaRepository.save(respostaPergunta);
	}
	
	public RespostaPergunta atualiza(RespostaPergunta respostaPergunta) {
		return respostaPerguntaRepository.save(respostaPergunta);
	}
	
	public Optional<RespostaPergunta> findById(Long id) {
		return respostaPerguntaRepository.findById(id);
	}
	
	public List<RespostaPergunta> findAll() {
		return respostaPerguntaRepository.findAll();
	}

	public List<RespostaPergunta> findByPergunta(Long id) {
		return respostaPerguntaRepository.findByPergunta(id);
	}

	public List<RespostaPergunta> findByAgricultorId(Long id) {
		return respostaPerguntaRepository.findByAgricultorId(id);
	}

	public void removeRespostasMultiplaEscolhaByAgricultor(Long id) {
		 respostaPerguntaRepository.removeRespostasMultiplaEscolhaByAgricultor(id);
	}
	
	public void remove(RespostaPergunta respostaPergunta) {
		respostaPerguntaRepository.delete(respostaPergunta);
	}
	
}
