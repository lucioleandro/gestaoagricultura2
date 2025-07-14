package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductImageRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductImageResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.ProductImageFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.ProductImageMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {

	private final ProductImageRepository productImageRepository;

	public ProductImageService(ProductImageRepository productImageRepository) {
		this.productImageRepository = productImageRepository;
	}

	@Transactional
	public ProductImageResponse create(ProductImageRequest productImage) {
		ProductImage entity = productImageRepository.save(
				ProductImageFactory.fromRequest(productImage)
		);
		return ProductImageMapper.toResponse(entity);
	}

	@Transactional
	public ProductImageResponse update(ProductImageRequest productImage) {
		ProductImage entity = productImageRepository.save(
				ProductImageFactory.fromRequest(productImage)
		);
		return ProductImageMapper.toResponse(entity);
	}

	public Optional<ProductImageResponse> findById(Long id) {
		return productImageRepository.findById(id)
				.map(ProductImageMapper::toResponse);
	}

	public List<ProductImageResponse> findAll() {
		return ProductImageMapper.toListResponse(
				productImageRepository.findAll()
		);
	}

	public List<ProductImageResponse> findByProductId(Long id) {
		return ProductImageMapper.toListResponse(
				productImageRepository.findByProductId(id)
		);
	}

	@Transactional
	public void remove(ProductImage productImage) {
		productImageRepository.delete(productImage);
	}
}
