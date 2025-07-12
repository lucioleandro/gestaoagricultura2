package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.RespostaPergunta;
import br.com.smart4.gestaoagriculturaapi.api.service.RespostaPerguntaService;
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
public class RespostaPerguntaController {

	@Autowired
	private RespostaPerguntaService respostaPerguntaService;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraRespostaPergunta(@RequestBody RespostaPergunta request) {
		try {
			return ResponseEntity.created(null).body(respostaPerguntaService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/cadastrarespostas")
	public ResponseEntity<?> cadastraRespostasPergunta(@Valid @RequestBody List<RespostaPergunta> request) {
		try {
			Long idAgricultor = request.get(0).getAgricultor().getId();
			this.DeletaRespostasMultiplaEscolhaByAgricultor(idAgricultor);
			
			for(RespostaPergunta resposta: request) {
				respostaPerguntaService.create(resposta);
			}
			
			return ResponseEntity.ok().body("");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private void DeletaRespostasMultiplaEscolhaByAgricultor(Long id) {
		try {
			respostaPerguntaService.removeRespostasMultiplaEscolhaByAgricultor(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaRespostaPergunta(@RequestBody RespostaPergunta request) {
		try {
			return ResponseEntity.ok().body(respostaPerguntaService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<RespostaPergunta> getListaRespostaPerguntaes() {
		try {
			return respostaPerguntaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/listabyagricultor")
	public List<RespostaPergunta> getListaRespostaPerguntaByPergunta(@Param(value = "id") Long id) {
		try {
			return respostaPerguntaService.findByAgricultorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeRespostaPergunta(@PathVariable Long id) {
		try {
			Optional<RespostaPergunta> respostaPergunta = respostaPerguntaService.findById(id);

			if (respostaPergunta.isPresent()) {
				respostaPerguntaService.remove(respostaPergunta.get());
				return ResponseEntity.ok().body("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.notFound().build();
	}
}
