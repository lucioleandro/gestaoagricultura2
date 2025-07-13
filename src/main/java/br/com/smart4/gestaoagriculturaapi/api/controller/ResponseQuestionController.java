package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.ResponseQuestion;
import br.com.smart4.gestaoagriculturaapi.api.service.ResponseQuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respostapergunta")
public class ResponseQuestionController {

	@Autowired
	private ResponseQuestionService respostaQuestionService;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraResponseQuestion(@RequestBody ResponseQuestion request) {
		try {
			return ResponseEntity.created(null).body(respostaQuestionService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/cadastrarespostas")
	public ResponseEntity<?> cadastraRespostasQuestion(@Valid @RequestBody List<ResponseQuestion> request) {
		try {
			Long idFarmer = request.get(0).getFarmer().getId();
			this.DeletaRespostasMultiplaEscolhaByFarmer(idFarmer);
			
			for(ResponseQuestion resposta: request) {
				respostaQuestionService.create(resposta);
			}
			
			return ResponseEntity.ok().body("");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private void DeletaRespostasMultiplaEscolhaByFarmer(Long id) {
		try {
			respostaQuestionService.removeRespostasMultiplaEscolhaByFarmer(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaResponseQuestion(@RequestBody ResponseQuestion request) {
		try {
			return ResponseEntity.ok().body(respostaQuestionService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<ResponseQuestion> getListaResponseQuestiones() {
		try {
			return respostaQuestionService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/listabyfarmer")
	public List<ResponseQuestion> getListaResponseQuestionByQuestion(@Param(value = "id") Long id) {
		try {
			return respostaQuestionService.findByFarmerId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeResponseQuestion(@PathVariable Long id) {
		try {
			Optional<ResponseQuestion> respostaQuestion = respostaQuestionService.findById(id);

			if (respostaQuestion.isPresent()) {
				respostaQuestionService.remove(respostaQuestion.get());
				return ResponseEntity.ok().body("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.notFound().build();
	}
}
