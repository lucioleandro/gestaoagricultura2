package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AddressResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.AddressService;
import jakarta.validation.Valid;
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
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody @Valid AddressRequest request) {
        return ResponseEntity.created(null).body(addressService.create(request));
    }

    @PutMapping
    public ResponseEntity<AddressResponse> update(@RequestBody @Valid AddressRequest request) {
        return ResponseEntity.ok().body(addressService.update(request));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getList() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAddress(@PathVariable Long id) {

        // TODO implementa este método levando a lógica para o service
        return ResponseEntity.notFound().build();
    }

}
