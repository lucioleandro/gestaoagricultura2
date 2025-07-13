package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.domains.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.services.ProductImageService;
import br.com.smart4.gestaoagriculturaapi.api.services.ProductService;
import br.com.smart4.gestaoagriculturaapi.api.utils.ResponseMessage;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productimage")
public class ProductImageController {

    private final ProductImageService productImageService;

    private final ProductService productService;

    public ProductImageController(ProductImageService productImageService, ProductService productService) {
        this.productImageService = productImageService;
        this.productService = productService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraProductImage(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("extensao") String extensao,
            @RequestParam("product") Long idProduct) throws IOException {

        Optional<Product> product = productService.findById(idProduct);

        if (product.isPresent()) {
            return ResponseEntity.created(null).body(productImageService
                    .create(new ProductImage(arquivo.getBytes(),
                            extensao, product.get())));
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("Este product não existe na base"));
        }
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaProductImage(@RequestBody ProductImage request) {
        return ResponseEntity.ok().body(productImageService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<ProductImage> getListaProductImages() {
        return productImageService.findAll();
    }

    @GetMapping("/listabyproduct")
    public List<ProductImage> getListaProductImagesByMunicipio(@Param(value = "id") Long id) {
        return productImageService.findByProductId(id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeProductImage(@PathVariable Long id) {
        Optional<ProductImage> productImage = productImageService.findById(id);

        if (productImage.isPresent()) {
            productImageService.remove(productImage.get());
            return ResponseEntity.ok().body("");
        }

        return ResponseEntity.notFound().build();
    }

}
