package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityFarmerResponse;

import java.util.List;

public class EconomicActivityFarmerMapper {

    private EconomicActivityFarmerMapper() {}

    public static EconomicActivityFarmerResponse toResponse(EconomicActivityFarmer entity) {
        if (entity == null) return null;

        return EconomicActivityFarmerResponse.builder()
                .id(entity.getId())
                .principal(entity.isPrincipal())
                .dataInicial(entity.getDataInicial())
                .dataFinal(entity.getDataFinal())
                .farmerId(entity.getFarmer() != null ? entity.getFarmer().getId() : null)
                .propertyId(entity.getProperty() != null ? entity.getProperty().getId() : null)
                .economicActivityId(entity.getEconomicActivity() != null ? entity.getEconomicActivity().getId() : null)
                .build();
    }
    public static List<EconomicActivityFarmerResponse> toListResponse(List<EconomicActivityFarmer> entities) {
        return entities.stream()
                .map(EconomicActivityFarmerMapper::toResponse)
                .toList();
    }

}

