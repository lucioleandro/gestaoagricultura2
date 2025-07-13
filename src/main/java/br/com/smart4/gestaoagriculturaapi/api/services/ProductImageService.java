package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductImageRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.ProductImageFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {

	private final ProductImageRepository productImageRepository;

	public ProductImageService(ProductImageRepository productImageRepository) {
		this.productImageRepository = productImageRepository;
	}

	public ProductImage create(ProductImageRequest productImage) {
		return productImageRepository.save(ProductImageFactory.fromRequest(productImage));
	}

	public ProductImage atualiza(ProductImageRequest productImage) {
		return productImageRepository.save(ProductImageFactory.fromRequest(productImage));
	}

	public Optional<ProductImage> findById(Long id) {
		return productImageRepository.findById(id);
	}

	public List<ProductImage> findAll() {
		return productImageRepository.findAll();
	}

	public List<ProductImage> findByProductId(Long id) {
		return productImageRepository.findByProductId(id);
	}

	public void remove(ProductImage productImage) {
		productImageRepository.delete(productImage);
	}
	
}
