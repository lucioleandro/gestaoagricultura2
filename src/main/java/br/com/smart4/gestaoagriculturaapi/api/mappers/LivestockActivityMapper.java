package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.LivestockActivityResponse;

public class LivestockActivityMapper {

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
}

