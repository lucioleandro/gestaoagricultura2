package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Pergunta;
import br.com.smart4.gestaoagriculturaapi.api.repository.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntaService {

	@Autowired
	private PerguntaRepository perguntaRepository;

	public Pergunta create(Pergunta pergunta) {
		return perguntaRepository.save(pergunta);
	}

	public Pergunta atualiza(Pergunta pergunta) {
		return perguntaRepository.save(pergunta);
	}

	public Optional<Pergunta> findById(Long id) {
		return perguntaRepository.findById(id);
	}

	public List<Pergunta> findAll() {
		return perguntaRepository.findAll();
	}

	public List<Pergunta> findPerguntasAtivas() {
		return perguntaRepository.findPerguntasAtivas();
	}
	
	public void remove(Pergunta pergunta) {
		perguntaRepository.delete(pergunta);
	}

}
