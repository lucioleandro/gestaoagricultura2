package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductImageResponse;

public class ProductImageMapper {

    public static ProductImageResponse toResponse(ProductImage entity) {
        if (entity == null) return null;

        return ProductImageResponse.builder()
                .id(entity.getId())
                .extensao(entity.getExtensao())
                .productId(entity.getProduct() != null ? entity.getProduct().getId() : null)
                .build();
    }
}

