package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AgricultureActivityRequest;

public final class AgricultureActivityFactory {

    private AgricultureActivityFactory() {}

    public static AgricultureActivity fromRequest(
            AgricultureActivityRequest request) {
        return AgricultureActivity.builder()
                .dataPlantio(request.getDataPlantio())
                .variedade(request.getVariedade())
                .areaPlantada(request.getAreaPlantada())
                .quantidadePlantas(request.getQuantidadePlantas())
                .producaoAnual(request.getProducaoAnual())
                .metodoIrrigacao(request.getMetodoIrrigacao())
                .fonteAgua(request.getFonteAgua())
                .product(Product.builder().id(request.getProductId()).build())
                .property(Property.builder().id(request.getPropertyId()).build())
                .build();
    }
}

