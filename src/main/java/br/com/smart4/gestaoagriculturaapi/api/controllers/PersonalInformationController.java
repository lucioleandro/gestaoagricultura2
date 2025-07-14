package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PersonalInformationRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PersonalInformationResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.PersonalInformationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personal-information")
public class PersonalInformationController {

    private final PersonalInformationService personalInformationService;

    public PersonalInformationController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @PostMapping
    public ResponseEntity<PersonalInformationResponse> create(@RequestBody @Valid PersonalInformationRequest request) {
        PersonalInformationResponse response = personalInformationService.create(request);
        return ResponseEntity.created(null).body(response);
    }

    @PutMapping
    public ResponseEntity<PersonalInformationResponse> update(@RequestBody @Valid PersonalInformationRequest request) {
        PersonalInformationResponse response = personalInformationService.update(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PersonalInformationResponse>> getList() {
        List<PersonalInformationResponse> responses = personalInformationService.findAll();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        Optional<PersonalInformationResponse> personalInformation = personalInformationService.findById(id);
//
//        if (personalInformation.isPresent()) {
//            // TODO: mover lógica de remoção para o service
//            personalInformationService.removeById(id);
//            return ResponseEntity.ok().build();
//        }

        return ResponseEntity.notFound().build();
    }
}
