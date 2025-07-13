package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.ResponseQuestionFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionResponseService {

	private final QuestionResponseRepository respostaQuestionRepository;

	public QuestionResponseService(QuestionResponseRepository respostaQuestionRepository) {
		this.respostaQuestionRepository = respostaQuestionRepository;
	}

	@Transactional
	public QuestionResponse create(ResponseQuestionRequest respostaQuestion) {
		return respostaQuestionRepository.save(ResponseQuestionFactory.fromRequest(respostaQuestion));
	}

	@Transactional
	public QuestionResponse atualiza(ResponseQuestionRequest respostaQuestion) {
		return respostaQuestionRepository.save(ResponseQuestionFactory.fromRequest(respostaQuestion));
	}
	
	public Optional<QuestionResponse> findById(Long id) {
		return respostaQuestionRepository.findById(id);
	}
	
	public List<QuestionResponse> findAll() {
		return respostaQuestionRepository.findAll();
	}

	public List<QuestionResponse> findByQuestion(Long id) {
		return respostaQuestionRepository.findByQuestionId(id);
	}

	public List<QuestionResponse> findByFarmerId(Long id) {
		return respostaQuestionRepository.findByFarmerId(id);
	}

	public void removeRespostasMultiplaEscolhaByFarmer(Long id) {
		 respostaQuestionRepository.removeRespostasMultiplaEscolhaByFarmer(id);
	}

	@Transactional
	public void remove(QuestionResponse respostaQuestion) {
		respostaQuestionRepository.delete(respostaQuestion);
	}
	
}
