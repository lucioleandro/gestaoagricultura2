package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.services.ProductService;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraProduct(@Valid @RequestBody Product request) {
        return ResponseEntity.created(null).body(productService.create(request));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaProduct(@RequestBody Product request) {
        return ResponseEntity.ok().body(productService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<Product> getListaProductes() {
        return productService.findAll();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            productService.remove(product.get());
            return ResponseEntity.ok().body("");
        }

        return ResponseEntity.notFound().build();
    }

}
