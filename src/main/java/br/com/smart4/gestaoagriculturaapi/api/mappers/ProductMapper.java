package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductResponse;

public class ProductMapper {

    public static ProductResponse toResponse(Product entity) {
        if (entity == null) return null;

        return ProductResponse.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .unidadeMedida(entity.getUnidadeMedida())
                .siglaUnidadeMedida(entity.getSiglaUnidadeMedida())
                .build();
    }
}

