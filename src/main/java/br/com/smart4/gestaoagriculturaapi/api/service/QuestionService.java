package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Question;
import br.com.smart4.gestaoagriculturaapi.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository perguntaRepository;

	public Question create(Question pergunta) {
		return perguntaRepository.save(pergunta);
	}

	public Question atualiza(Question pergunta) {
		return perguntaRepository.save(pergunta);
	}

	public Optional<Question> findById(Long id) {
		return perguntaRepository.findById(id);
	}

	public List<Question> findAll() {
		return perguntaRepository.findAll();
	}

	public List<Question> findQuestionsAtivas() {
		return perguntaRepository.findQuestionsAtivas();
	}
	
	public void remove(Question pergunta) {
		perguntaRepository.delete(pergunta);
	}

}
