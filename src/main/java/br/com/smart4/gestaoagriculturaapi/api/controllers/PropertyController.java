package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PropertyRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PropertyResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.AddressService;
import br.com.smart4.gestaoagriculturaapi.api.services.PropertyService;
import br.com.smart4.gestaoagriculturaapi.api.utils.Coordinates;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final AddressService addressService;

    public PropertyController(PropertyService propertyService, AddressService addressService) {
        this.propertyService = propertyService;
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<PropertyResponse> create(@RequestBody @Valid PropertyRequest request) {
        Coordinates coordenadas = searchCoordinates(request.getAddress());
        request.setLatitude(coordenadas.getLatitude());
        request.setLongitude(coordenadas.getLongitude());

        addressService.create(request.getAddress());
        PropertyResponse created = propertyService.create(request);

        return ResponseEntity.created(null).body(created);
    }

    @PutMapping
    public ResponseEntity<PropertyResponse> update(@RequestBody @Valid PropertyRequest request) {
        Coordinates coordenadas = searchCoordinates(request.getAddress());
        request.setLatitude(coordenadas.getLatitude());
        request.setLongitude(coordenadas.getLongitude());

        addressService.update(request.getAddress());
        return ResponseEntity.ok().body(propertyService.update(request));
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getList() {
        return ResponseEntity.ok(propertyService.findAll());
    }

    @GetMapping("/farmer")
    public ResponseEntity<List<PropertyResponse>> getListByFarmer(@Param(value = "id") Long farmerId) {
        return ResponseEntity.ok(propertyService.findByFarmer(farmerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        // TODO: Mover essa lógica de remoção para dentro do PropertyService
//        Optional<PropertyResponse> property = propertyService.findEntityById(id);
//
//        property.ifPresent(prop -> {
//            Optional<Address> address = addressService.findById(prop.getAddressId());
//            address.ifPresent(addressService::remove);
//            propertyService.removeById(id);
//        });

        return ResponseEntity.ok().build();
    }

    public Coordinates searchCoordinates(AddressRequest address) {
        // TODO: Substituir pelo uso real da API do Google Maps se necessário.
        return new Coordinates(null, null);
    }
}
