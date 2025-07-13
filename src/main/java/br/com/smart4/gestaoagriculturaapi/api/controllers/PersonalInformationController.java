package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PersonalInformationRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.PersonalInformationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personalinformation")
public class PersonalInformationController {

    private final PersonalInformationService personalInformationService;

    public PersonalInformationController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraPersonalInformation(@RequestBody @Valid PersonalInformationRequest request) {
        return ResponseEntity.created(null).body(personalInformationService.create(request));
    }

    @PostMapping("/atualiza")
    public ResponseEntity<?> atualizaPersonalInformation(@RequestBody @Valid PersonalInformationRequest request) {
        return ResponseEntity.ok().body(personalInformationService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<PersonalInformation> getListaPersonalInformationes() {
        return personalInformationService.findAll();
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removePersonalInformation(@Valid @RequestBody Long id) {
        Optional<PersonalInformation> personalInformation = personalInformationService.findById(id);

        if (personalInformation.isPresent()) {
            personalInformationService.remove(personalInformation.get());
            return ResponseEntity.ok().body("");
        }

        return ResponseEntity.notFound().build();
    }

}
