package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.ResponseQuestion;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.ResponseQuestionFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ResponseQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseQuestionService {

	private final ResponseQuestionRepository respostaQuestionRepository;

	public ResponseQuestionService(ResponseQuestionRepository respostaQuestionRepository) {
		this.respostaQuestionRepository = respostaQuestionRepository;
	}

	public ResponseQuestion create(ResponseQuestionRequest respostaQuestion) {
		return respostaQuestionRepository.save(ResponseQuestionFactory.fromRequest(respostaQuestion));
	}
	
	public ResponseQuestion atualiza(ResponseQuestionRequest respostaQuestion) {
		return respostaQuestionRepository.save(ResponseQuestionFactory.fromRequest(respostaQuestion));
	}
	
	public Optional<ResponseQuestion> findById(Long id) {
		return respostaQuestionRepository.findById(id);
	}
	
	public List<ResponseQuestion> findAll() {
		return respostaQuestionRepository.findAll();
	}

	public List<ResponseQuestion> findByQuestion(Long id) {
		return respostaQuestionRepository.findByQuestionId(id);
	}

	public List<ResponseQuestion> findByFarmerId(Long id) {
		return respostaQuestionRepository.findByFarmerId(id);
	}

	public void removeRespostasMultiplaEscolhaByFarmer(Long id) {
		 respostaQuestionRepository.removeRespostasMultiplaEscolhaByFarmer(id);
	}
	
	public void remove(ResponseQuestion respostaQuestion) {
		respostaQuestionRepository.delete(respostaQuestion);
	}
	
}
