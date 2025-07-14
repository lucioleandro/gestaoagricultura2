package br.com.smart4.gestaoagriculturaapi.sistema.mappers;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Compatible;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.CompatibleResponse;

import java.util.List;

public class CompatibleMapper {

    private CompatibleMapper() {}

    public static CompatibleResponse toResponse(Compatible entity) {
        if (entity == null) return null;

        return CompatibleResponse.builder()
                .id(entity.getId())
                .codSistema(entity.getCodSistema())
                .versaoLiberada(entity.getVersaoLiberada())
                .build();
    }

    public static List<CompatibleResponse> toListResponse(List<Compatible> entities) {
        return entities.stream()
                .map(CompatibleMapper::toResponse)
                .toList();
    }

}

