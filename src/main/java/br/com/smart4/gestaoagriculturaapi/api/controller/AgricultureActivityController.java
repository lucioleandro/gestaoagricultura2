package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.service.AgricultureActivityService;
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
@RequestMapping("/agricultureactivity")
public class AgricultureActivityController {

	@Autowired
	private AgricultureActivityService agricultureActivityService;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraAgricultureActivity(@RequestBody AgricultureActivity request) {
		try {
			return ResponseEntity.created(null).body(agricultureActivityService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaAgricultureActivity(@RequestBody AgricultureActivity request) {
		try {
			agricultureActivityService.atualiza(request);

			return ResponseEntity.ok().body("");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<AgricultureActivity> getListaAgricultureActivity() {
		try {
			return agricultureActivityService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping("/listabyproperty")
	@ResponseBody
	public List<AgricultureActivity> getListaAgricultureActivityByProperty(@Param(value = "id") Long id) {
		try {
			return agricultureActivityService.findByProperty(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeAgricultureActivity(@PathVariable Long id) {
		try {
			Optional<AgricultureActivity> agricultureActivity = agricultureActivityService.findById(id);

			if (agricultureActivity.isPresent()) {
				agricultureActivityService.remove(agricultureActivity.get());
				return ResponseEntity.ok().body("");
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

}
