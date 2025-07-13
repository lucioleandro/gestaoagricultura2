package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AgricultureActivityResponse;

public class AgricultureActivityMapper {

    public static AgricultureActivityResponse toResponse(AgricultureActivity activity) {
        if (activity == null) {
            return null;
        }

        return AgricultureActivityResponse.builder()
                .id(activity.getId())
                .dataPlantio(activity.getDataPlantio())
                .variedade(activity.getVariedade())
                .areaPlantada(activity.getAreaPlantada())
                .quantidadePlantas(activity.getQuantidadePlantas())
                .producaoAnual(activity.getProducaoAnual())
                .metodoIrrigacao(activity.getMetodoIrrigacao())
                .fonteAgua(activity.getFonteAgua())
                .propertyId(activity.getProperty() != null ? activity.getProperty().getId() : null)
                .propertyNome(activity.getProperty() != null ? activity.getProperty().getNome() : null)
                .productId(activity.getProduct() != null ? activity.getProduct().getId() : null)
                .productDescricao(activity.getProduct() != null ? activity.getProduct().getDescricao() : null)
                .build();
    }
}

