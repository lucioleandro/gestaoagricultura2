package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductImageRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductImageResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.ProductImageService;
import br.com.smart4.gestaoagriculturaapi.api.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Tag(name = "Product Image", description = "Endpoints for managing product images")
@RestController
@RequestMapping("/product-images")
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @Operation(summary = "Upload a new product image", description = "Uploads a new image for a given product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Image uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product or file", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error processing file", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProductImageResponse> create(
            @Parameter(description = "Image file to upload", required = true)
            @RequestParam("arquivo") MultipartFile arquivo,

            @Parameter(description = "Extension of the file", required = true)
            @RequestParam("extensao") String extensao,

            @Parameter(description = "Product ID", required = true)
            @RequestParam("product") Long productId
    ) throws IOException {
        var resp = productImageService.create(arquivo, extensao, productId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resp.getId())
                .toUri();

        return ResponseEntity.created(location).body(resp);
    }

    @Operation(summary = "Update a product image", description = "Updates an existing image with new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image updated successfully"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductImageResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid ProductImageRequest request) {
        return ResponseEntity.ok(productImageService.update(id, request));
    }

    @Operation(summary = "List all product images", description = "Returns all product images stored in the system")
    @ApiResponse(responseCode = "200", description = "List returned successfully")
    @GetMapping
    public ResponseEntity<List<ProductImageResponse>> getList() {
        return ResponseEntity.ok(productImageService.findAll());
    }

    @Operation(summary = "List product images by product ID", description = "Returns images associated with a specific product")
    @ApiResponse(responseCode = "200", description = "Images retrieved successfully")
    @GetMapping("/product")
    public ResponseEntity<List<ProductImageResponse>> getListByProductId(
            @RequestParam("id") Long productId) {
        return ResponseEntity.ok(productImageService.findByProductId(productId));
    }

    @Operation(summary = "Delete a product image", description = "Deletes a specific image by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Image deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        productImageService.remove(id);
        return ResponseEntity.notFound().build();
    }
}
