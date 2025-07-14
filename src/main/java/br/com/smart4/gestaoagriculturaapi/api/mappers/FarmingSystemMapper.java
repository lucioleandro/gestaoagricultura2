package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.FarmingSystem;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.FarmingSystemResponse;

import java.util.List;

public class FarmingSystemMapper {

    private FarmingSystemMapper() {}

    public static FarmingSystemResponse toResponse(FarmingSystem entity) {
        if (entity == null) return null;

        return FarmingSystemResponse.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .ramoAtividade(entity.getRamoAtividade())
                .build();
    }

    public static List<FarmingSystemResponse> toListResponse(List<FarmingSystem> entities) {
        return entities.stream()
                .map(FarmingSystemMapper::toResponse)
                .toList();
    }

}

