package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.QuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.QuestionFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

	private final QuestionRepository perguntaRepository;

	public QuestionService(QuestionRepository perguntaRepository) {
		this.perguntaRepository = perguntaRepository;
	}

	public Question create(QuestionRequest pergunta) {
		return perguntaRepository.save(QuestionFactory.fromRequest(pergunta));
	}

	public Question atualiza(QuestionRequest pergunta) {
		return perguntaRepository.save(QuestionFactory.fromRequest(pergunta));
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
