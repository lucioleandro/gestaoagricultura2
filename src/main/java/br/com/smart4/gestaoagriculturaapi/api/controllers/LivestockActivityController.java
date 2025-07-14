package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.LivestockActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.LivestockActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.LivestockActivityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livestock-activities")
public class LivestockActivityController {

    private final LivestockActivityService livestockActivityService;

    public LivestockActivityController(LivestockActivityService livestockActivityService) {
        this.livestockActivityService = livestockActivityService;
    }

    @PostMapping
    public ResponseEntity<LivestockActivityResponse> create(@RequestBody @Valid LivestockActivityRequest request) {
        return ResponseEntity.created(null).body(livestockActivityService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivestockActivityResponse> update(@PathVariable Long id, @RequestBody @Valid LivestockActivityRequest request) {
        return ResponseEntity.ok().body(livestockActivityService.update(id, request));
    }

    @GetMapping
    public ResponseEntity<List<LivestockActivityResponse>> getList() {
        return ResponseEntity.ok(livestockActivityService.findAll());
    }

    @GetMapping("/property")
    public ResponseEntity<List<LivestockActivityResponse>> getListByProperty(@RequestParam("id") Long propertyId) {
        List<LivestockActivityResponse> responses = livestockActivityService.findByPropertyId(propertyId);
        return ResponseEntity.ok(responses);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
//        Optional<LivestockActivity> livestockActivity = livestockActivityService.findById(id);
//
//        if (livestockActivity.isPresent()) {
//            livestockActivityService.remove(livestockActivity.get());
//            return ResponseEntity.ok().body("");
//        }

        // TODO levar lógica para o service
        return ResponseEntity.notFound().build();

    }
}

