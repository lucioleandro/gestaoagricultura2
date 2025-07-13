package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
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
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraAddress(@RequestBody @Valid AddressRequest request) {
        return ResponseEntity.created(null).body(addressService.create(request));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaAddress(@RequestBody AddressRequest request) {
        return ResponseEntity.ok().body(addressService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<Address> getListaAddresses() {
        return addressService.findAll();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeAddress(@PathVariable Long id) {
        Optional<Address> address = addressService.findById(id);

        if (address.isPresent()) {
            addressService.remove(address.get());
            return ResponseEntity.ok().body("");
        }

        return ResponseEntity.notFound().build();
    }

}
