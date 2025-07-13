package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.domain.Product;
import br.com.smart4.gestaoagriculturaapi.api.service.ProductImageService;
import br.com.smart4.gestaoagriculturaapi.api.service.ProductService;
import br.com.smart4.gestaoagriculturaapi.api.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagemproduct")
public class ProductImageController {
	
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private ProductService productService;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraProductImage(
												   @RequestParam("arquivo") MultipartFile arquivo,
												   @RequestParam("extensao") String extensao,
												   @RequestParam("product") Long idProduct) {

		try {
			Optional<Product> product = productService.findById(idProduct);
			
			if(product.isPresent()) {
				return ResponseEntity.created(null).body(productImageService
												.create(new ProductImage(arquivo.getBytes(), 
													extensao, product.get())));
			} else {
				return ResponseEntity.badRequest().body(new ResponseMessage("Este product não existe na base"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaProductImage(@RequestBody ProductImage request) {
		try {
			return ResponseEntity.ok().body(productImageService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<ProductImage> getListaProductImages() {
		try {
			return productImageService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyproduct")
	public List<ProductImage> getListaProductImagesByMunicipio(@Param(value = "id") Long id) {
		try {
			return productImageService.findByProductId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeProductImage(@PathVariable Long id) {
		try {
			Optional<ProductImage> productImage = productImageService.findById(id);

			if (productImage.isPresent()) {
				productImageService.remove(productImage.get());
				return ResponseEntity.ok().body("");
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
