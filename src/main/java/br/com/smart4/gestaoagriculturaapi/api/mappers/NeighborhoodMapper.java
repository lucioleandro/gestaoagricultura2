package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.NeighborhoodResponse;

import java.util.List;

public class NeighborhoodMapper {

    private NeighborhoodMapper() {}

    public static NeighborhoodResponse toResponse(Neighborhood entity) {
        if (entity == null) return null;

        return NeighborhoodResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .zona(entity.getZona())
                .cityId(entity.getCity() != null ? entity.getCity().getId() : null)
                .cityNome(entity.getCity() != null ? entity.getCity().getNome() : null)
                .build();
    }

    public static List<NeighborhoodResponse> toListResponse(List<Neighborhood> entities) {
        return entities.stream()
                .map(NeighborhoodMapper::toResponse)
                .toList();
    }

}

