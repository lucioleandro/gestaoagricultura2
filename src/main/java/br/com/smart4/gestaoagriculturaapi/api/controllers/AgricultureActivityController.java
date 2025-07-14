package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AgricultureActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AgricultureActivityResponse;
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
    public ResponseEntity<AgricultureActivityResponse> create(@RequestBody @Valid AgricultureActivityRequest request) {
        return ResponseEntity.created(null).body(agricultureActivityService.create(request));
    }

    @PutMapping
    public ResponseEntity<AgricultureActivityResponse> update(@RequestBody @Valid AgricultureActivityRequest request) {
        return ResponseEntity.ok().body(agricultureActivityService.update(request));
    }

    @GetMapping
    public ResponseEntity<List<AgricultureActivityResponse>> getList() {
        return ResponseEntity.ok(agricultureActivityService.findAll());
    }

    @GetMapping("/property")
    public ResponseEntity<List<AgricultureActivityResponse>> getListByProperty(@Param(value = "id") Long id) {
        return ResponseEntity.ok(agricultureActivityService.findByProperty(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        Optional<AgricultureActivity> agricultureActivity = agricultureActivityService.findById(id);
//
//        if (agricultureActivity.isPresent()) {
//            agricultureActivityService.remove(agricultureActivity.get());
//            return ResponseEntity.ok().body("");
//        }
        // TODO levar a lógica para o service
        return ResponseEntity.notFound().build();
    }

}
