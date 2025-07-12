package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadeEconomicaAgricultor;
import br.com.smart4.gestaoagriculturaapi.api.domain.Propriedade;
import br.com.smart4.gestaoagriculturaapi.api.service.AtividadeEconomicaAgricultorService;
import br.com.smart4.gestaoagriculturaapi.api.util.ResponseMessage;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividadeeconomicaagricultor")
public class AtividadeEconomicaAgricultorController {
	
	@Autowired
	private AtividadeEconomicaAgricultorService atividadeEconomicaAgricultorService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraAtividadeEconomicaAgricultor(@RequestBody AtividadeEconomicaAgricultor request) {
		try {
			if(request.isPrincipal() && existeAtividadePrincipal(request.getPropriedade())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("Já existe uma atividade como principal"));
			}
			
			return ResponseEntity.created(null).body(atividadeEconomicaAgricultorService.create(request));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private boolean existeAtividadePrincipal(Propriedade propriedade) {
		List<AtividadeEconomicaAgricultor> atividades = atividadeEconomicaAgricultorService
				.findByPropriedade(propriedade.getId());
		for (AtividadeEconomicaAgricultor atv : atividades) {
			if(atv.isPrincipal()) {
				return true;
			}
		}
		return false;
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaAtividadeEconomicaAgricultor(@RequestBody AtividadeEconomicaAgricultor request) {
		try {
			if(request.isPrincipal() && existeAtividadePrincipal(request.getPropriedade())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("Já existe uma atividade como principal"));
			}
			
			return ResponseEntity.ok().body(atividadeEconomicaAgricultorService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<AtividadeEconomicaAgricultor> getListaAtividadeEconomica() {
		try {
			return atividadeEconomicaAgricultorService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyagricultor")
	public List<AtividadeEconomicaAgricultor> getListaAtividadeByAgricultor(@Param(value = "id") Long id) {
		try {
			
			return atividadeEconomicaAgricultorService.findByAgricultor(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabypropriedade")
	@ResponseBody
	public List<AtividadeEconomicaAgricultor> getListaAtividadeByPropriedade(@Param(value = "id") Long id) {
		try {
			return atividadeEconomicaAgricultorService.findByPropriedade(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeAtividadeEconomicaAgricultor(@PathVariable Long id) {
		try {
			Optional<AtividadeEconomicaAgricultor> atividadeEconomicaAgricultor = atividadeEconomicaAgricultorService.findById(id);

			if (atividadeEconomicaAgricultor.isPresent()) {
				atividadeEconomicaAgricultorService.remove(atividadeEconomicaAgricultor.get());
				return ResponseEntity.ok().body("");
			} 
				
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
