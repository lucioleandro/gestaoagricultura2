package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.FarmingSystem;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.FarmingSystemResponse;

public class FarmingSystemMapper {

    public static FarmingSystemResponse toResponse(FarmingSystem entity) {
        if (entity == null) return null;

        return FarmingSystemResponse.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .ramoAtividade(entity.getRamoAtividade())
                .build();
    }
}

