package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AnsweredQuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.ResponseQuestionFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.QuestionResponseMapper;
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
	public AnsweredQuestionResponse create(ResponseQuestionRequest respostaQuestion) {
		QuestionResponse entity = respostaQuestionRepository.save(
				ResponseQuestionFactory.fromRequest(respostaQuestion)
		);
		return QuestionResponseMapper.toResponse(entity);
	}

	@Transactional
	public AnsweredQuestionResponse update(ResponseQuestionRequest respostaQuestion) {
		QuestionResponse entity = respostaQuestionRepository.save(
				ResponseQuestionFactory.fromRequest(respostaQuestion)
		);
		return QuestionResponseMapper.toResponse(entity);
	}

	public Optional<AnsweredQuestionResponse> findById(Long id) {
		return respostaQuestionRepository.findById(id)
				.map(QuestionResponseMapper::toResponse);
	}

	public List<AnsweredQuestionResponse> findAll() {
		return QuestionResponseMapper.toListResponse(
				respostaQuestionRepository.findAll()
		);
	}

	public List<AnsweredQuestionResponse> findByQuestion(Long id) {
		return QuestionResponseMapper.toListResponse(
				respostaQuestionRepository.findByQuestionId(id)
		);
	}

	public List<AnsweredQuestionResponse> findByFarmerId(Long id) {
		return QuestionResponseMapper.toListResponse(
				respostaQuestionRepository.findByFarmerId(id)
		);
	}

	public void removeRespostasMultiplaEscolhaByFarmer(Long id) {
		respostaQuestionRepository.removeRespostasMultiplaEscolhaByFarmer(id);
	}

	@Transactional
	public void remove(QuestionResponse respostaQuestion) {
		respostaQuestionRepository.delete(respostaQuestion);
	}
}
