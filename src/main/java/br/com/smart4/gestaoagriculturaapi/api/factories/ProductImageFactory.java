package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.domains.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductImageRequest;

public final class ProductImageFactory {

    private ProductImageFactory() {}

    public static ProductImage fromRequest(ProductImageRequest request) {
        return ProductImage.builder()
                .arquivo(request.getArquivo())
                .extensao(request.getExtensao())
                .product(Product.builder().id(request.getProductId()).build())
                .build();
    }
}

