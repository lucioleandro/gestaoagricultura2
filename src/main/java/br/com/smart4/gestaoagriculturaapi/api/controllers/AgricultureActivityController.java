package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AgricultureActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.AgricultureActivityService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agriculture-activities")
public class AgricultureActivityController {

    private final AgricultureActivityService agricultureActivityService;

    public AgricultureActivityController(AgricultureActivityService agricultureActivityService) {
        this.agricultureActivityService = agricultureActivityService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AgricultureActivityRequest request) {
        return ResponseEntity.created(null).body(agricultureActivityService.create(request));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid AgricultureActivityRequest request) {
        agricultureActivityService.update(request);
        return ResponseEntity.ok().body("");
    }

    @GetMapping
    public List<AgricultureActivity> getList() {
        return agricultureActivityService.findAll();
    }

    @GetMapping("/property")
    public List<AgricultureActivity> getListByProperty(@Param(value = "id") Long id) {
        return agricultureActivityService.findByProperty(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<AgricultureActivity> agricultureActivity = agricultureActivityService.findById(id);

        if (agricultureActivity.isPresent()) {
            agricultureActivityService.remove(agricultureActivity.get());
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.notFound().build();
    }

}
