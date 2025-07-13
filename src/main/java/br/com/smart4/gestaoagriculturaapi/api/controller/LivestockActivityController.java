package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.service.LivestockActivityService;
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
@RequestMapping("/livestockactivity")
public class LivestockActivityController {

	@Autowired
	private LivestockActivityService livestockActivityService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraLivestockActivity(@RequestBody LivestockActivity request) {
		try {
			return ResponseEntity.created(null).body(livestockActivityService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaLivestockActivity(@RequestBody LivestockActivity request) {
		try {
			return ResponseEntity.ok().body(livestockActivityService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<LivestockActivity> getListaLivestockActivity() {
		try {
			return livestockActivityService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyproperty")
	public List<LivestockActivity> getListaLivestockActivityByProperty(@Param(value = "id") Long id) {
		try {
			return livestockActivityService.findByPropertyId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeLivestockActivity(@PathVariable Long id) {
		try {
			Optional<LivestockActivity> livestockActivity = livestockActivityService.findById(id);

			if (livestockActivity.isPresent()) {
				livestockActivityService.remove(livestockActivity.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}

