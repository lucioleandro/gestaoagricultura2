package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.LivestockActivityResponse;

import java.util.List;

public class LivestockActivityMapper {

    private LivestockActivityMapper() {}

    public static LivestockActivityResponse toResponse(LivestockActivity entity) {
        if (entity == null) return null;

        return LivestockActivityResponse.builder()
                .id(entity.getId())
                .especie(entity.getEspecie())
                .raca(entity.getRaca())
                .quantidade(entity.getQuantidade())
                .propertyId(entity.getProperty() != null ? entity.getProperty().getId() : null)
                .build();
    }

    public static List<LivestockActivityResponse> toListResponse(List<LivestockActivity> entities) {
        return entities.stream()
                .map(LivestockActivityMapper::toResponse)
                .toList();
    }

}

