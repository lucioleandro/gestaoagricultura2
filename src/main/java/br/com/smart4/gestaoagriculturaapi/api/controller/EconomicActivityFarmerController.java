package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.domain.Property;
import br.com.smart4.gestaoagriculturaapi.api.service.EconomicActivityFarmerService;
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
@RequestMapping("/economicactivityfarmer")
public class EconomicActivityFarmerController {
	
	@Autowired
	private EconomicActivityFarmerService economicActivityFarmerService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraEconomicActivityFarmer(@RequestBody EconomicActivityFarmer request) {
		try {
			if(request.isPrincipal() && existeAtividadePrincipal(request.getProperty())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("Já existe uma atividade como principal"));
			}
			
			return ResponseEntity.created(null).body(economicActivityFarmerService.create(request));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private boolean existeAtividadePrincipal(Property property) {
		List<EconomicActivityFarmer> atividades = economicActivityFarmerService
				.findByProperty(property.getId());
		for (EconomicActivityFarmer atv : atividades) {
			if(atv.isPrincipal()) {
				return true;
			}
		}
		return false;
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaEconomicActivityFarmer(@RequestBody EconomicActivityFarmer request) {
		try {
			if(request.isPrincipal() && existeAtividadePrincipal(request.getProperty())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("Já existe uma atividade como principal"));
			}
			
			return ResponseEntity.ok().body(economicActivityFarmerService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<EconomicActivityFarmer> getListaEconomicActivity() {
		try {
			return economicActivityFarmerService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyfarmer")
	public List<EconomicActivityFarmer> getListaAtividadeByFarmer(@Param(value = "id") Long id) {
		try {
			
			return economicActivityFarmerService.findByFarmer(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyproperty")
	@ResponseBody
	public List<EconomicActivityFarmer> getListaAtividadeByProperty(@Param(value = "id") Long id) {
		try {
			return economicActivityFarmerService.findByProperty(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeEconomicActivityFarmer(@PathVariable Long id) {
		try {
			Optional<EconomicActivityFarmer> economicActivityFarmer = economicActivityFarmerService.findById(id);

			if (economicActivityFarmer.isPresent()) {
				economicActivityFarmerService.remove(economicActivityFarmer.get());
				return ResponseEntity.ok().body("");
			} 
				
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
