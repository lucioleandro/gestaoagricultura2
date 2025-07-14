package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductImageRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductImageResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.ProductImageService;
import br.com.smart4.gestaoagriculturaapi.api.services.ProductService;
import br.com.smart4.gestaoagriculturaapi.api.utils.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product-images")
public class ProductImageController {

    private final ProductImageService productImageService;
    private final ProductService productService;

    public ProductImageController(ProductImageService productImageService, ProductService productService) {
        this.productImageService = productImageService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("extensao") String extensao,
            @RequestParam("product") Long idProduct) throws IOException {

        // TODO levar para o service
//        Optional<ProductImageResponse> created = productService.findById(idProduct)
//                .map(product -> {
//                    ProductImageRequest request = null;
//                    try {
//                        request = new ProductImageRequest(arquivo.getBytes(), extensao, product.getId());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    return productImageService.create(request);
//                });

//        return created
//                .map(response -> {
//                    URI location = URI.create("/product-images/" + response.getId()); // só use se houver getById
//                    return ResponseEntity.created(location).body(response);
//                })
//                .orElseGet(() -> ResponseEntity.badRequest().body(null));
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductImageResponse> update(@PathVariable Long id, @RequestBody @Valid ProductImageRequest request) {
        return ResponseEntity.ok(productImageService.update(id, request));
    }

    @GetMapping
    public ResponseEntity<List<ProductImageResponse>> getList() {
        return ResponseEntity.ok(productImageService.findAll());
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductImageResponse>> getListByProductId(@RequestParam("id") Long productId) {
        return ResponseEntity.ok(productImageService.findByProductId(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        Optional<ProductImageResponse> image = productImageService.findById(id);
//
//        if (image.isPresent()) {
//            // TODO: mover lógica de remoção para o service
//            productImageService.removeById(id);
//            return ResponseEntity.ok().build();
//        }

        return ResponseEntity.notFound().build();
    }
}
