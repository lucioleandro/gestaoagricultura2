package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request) {
        ProductResponse response = productService.create(request);
        return ResponseEntity.created(null).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,
                                                  @RequestBody @Valid ProductRequest request) {
        ProductResponse response = productService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getList() {
        List<ProductResponse> responses = productService.findAll();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        Optional<ProductResponse> product = productService.findById(id);
//
//        if (product.isPresent()) {
//            // TODO: mover lógica de remoção para o service
//            productService.removeById(id);
//            return ResponseEntity.ok().build();
//        }

        return ResponseEntity.notFound().build();
    }
}
