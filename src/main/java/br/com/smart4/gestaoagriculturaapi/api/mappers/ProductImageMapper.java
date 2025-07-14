package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductImageResponse;

import java.util.List;

public class ProductImageMapper {

    private ProductImageMapper() {}

    public static ProductImageResponse toResponse(ProductImage entity) {
        if (entity == null) return null;

        return ProductImageResponse.builder()
                .id(entity.getId())
                .extensao(entity.getExtensao())
                .productId(entity.getProduct() != null ? entity.getProduct().getId() : null)
                .build();
    }

    public static List<ProductImageResponse> toListResponse(List<ProductImage> entities) {
        return entities.stream()
                .map(ProductImageMapper::toResponse)
                .toList();
    }

}

