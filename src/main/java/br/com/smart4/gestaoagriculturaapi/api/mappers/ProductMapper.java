package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductResponse;

import java.util.List;

public class ProductMapper {

    private ProductMapper() {}

    public static ProductResponse toResponse(Product entity) {
        if (entity == null) return null;

        return ProductResponse.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .unidadeMedida(entity.getUnidadeMedida())
                .siglaUnidadeMedida(entity.getSiglaUnidadeMedida())
                .build();
    }

    public static List<ProductResponse> toListResponse(List<Product> entities) {
        return entities.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

}

