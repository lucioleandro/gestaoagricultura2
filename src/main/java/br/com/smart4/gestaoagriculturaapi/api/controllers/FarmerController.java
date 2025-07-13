package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.FarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.FarmerService;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping
    public ResponseEntity<?> cadastraFarmer(@RequestBody @Valid FarmerRequest request,
                                            UriComponentsBuilder uriBuilder) throws InvalidStateException {
        new CPFValidator().assertValid(request.getCpf());

        URI uri = uriBuilder.path("/farmer/getbycpf?cpf={cpf}")
                .buildAndExpand(request.getCpf()).toUri();

        return ResponseEntity.created(uri).body(farmerService.create(request));

    }

    @PutMapping
    public ResponseEntity<?> atualizaFarmer(@RequestBody @Valid FarmerRequest request) {
        new CPFValidator().assertValid(request.getCpf());

        return ResponseEntity.ok().body(farmerService.atualiza(request));
    }

    @GetMapping
    public List<Farmer> getListaFarmeres() {
        return farmerService.findAll();
    }

    @GetMapping
    public Farmer getFarmerByCpf(@Param(value = "cpf") String cpf) {
        Optional<Farmer> farmer = farmerService.findByCpf(cpf);

        if (farmer.isPresent()) {
            return farmer.get();
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFarmer(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerService.findById(id);

        if (farmer.isPresent()) {
            farmerService.remove(farmer.get());
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.notFound().build();
    }

}
