package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductRequest;

public final class ProductFactory {

    private ProductFactory() {}

    public static Product fromRequest(ProductRequest request) {
        return Product.builder()
                .descricao(request.getDescricao())
                .unidadeMedida(request.getUnidadeMedida())
                .siglaUnidadeMedida(request.getSiglaUnidadeMedida())
                .build();
    }
}

