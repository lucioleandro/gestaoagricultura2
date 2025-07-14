package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.domains.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AnsweredQuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.ResponseQuestionFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.QuestionResponseMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionResponseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionResponseService {

	private final QuestionResponseRepository respostaQuestionRepository;
	private final QuestionRepository questionRepository;
	private final FarmerRepository farmerRepository;

	public QuestionResponseService(
			QuestionResponseRepository respostaQuestionRepository,
			QuestionRepository questionRepository,
			FarmerRepository farmerRepository
	) {
		this.respostaQuestionRepository = respostaQuestionRepository;
		this.questionRepository = questionRepository;
		this.farmerRepository = farmerRepository;
	}

	@Transactional
	public AnsweredQuestionResponse create(ResponseQuestionRequest respostaQuestion) {
		QuestionResponse entity = respostaQuestionRepository.save(
				ResponseQuestionFactory.fromRequest(respostaQuestion)
		);
		return QuestionResponseMapper.toResponse(entity);
	}

	@Transactional
	public AnsweredQuestionResponse update(Long id, ResponseQuestionRequest request) {
		QuestionResponse existing = respostaQuestionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("QuestionResponse not found with id: " + id));

		Question question = questionRepository.findById(request.getQuestionId())
				.orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + request.getQuestionId()));

		Farmer farmer = farmerRepository.findById(request.getFarmerId())
				.orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + request.getFarmerId()));

		existing.setDescricao(request.getDescricao());
		existing.setQuestion(question);
		existing.setFarmer(farmer);

		return QuestionResponseMapper.toResponse(respostaQuestionRepository.save(existing));
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

	@Transactional
	public void removeRespostasMultiplaEscolhaByFarmer(Long id) {
		respostaQuestionRepository.removeRespostasMultiplaEscolhaByFarmer(id);
	}

	@Transactional
	public void remove(QuestionResponse respostaQuestion) {
		respostaQuestionRepository.delete(respostaQuestion);
	}
}
