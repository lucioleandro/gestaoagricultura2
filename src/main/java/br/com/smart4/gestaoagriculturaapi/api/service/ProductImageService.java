package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;
	
	public ProductImage create(ProductImage productImage) {
		return productImageRepository.save(productImage);
	}

	public ProductImage atualiza(ProductImage productImage) {
		return productImageRepository.save(productImage);
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
