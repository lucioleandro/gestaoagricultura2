package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.ResponseQuestion;
import br.com.smart4.gestaoagriculturaapi.api.repository.ResponseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseQuestionService {

	@Autowired
	private ResponseQuestionRepository respostaQuestionRepository;
	
	public ResponseQuestion create(ResponseQuestion respostaQuestion) {
		return respostaQuestionRepository.save(respostaQuestion);
	}
	
	public ResponseQuestion atualiza(ResponseQuestion respostaQuestion) {
		return respostaQuestionRepository.save(respostaQuestion);
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
