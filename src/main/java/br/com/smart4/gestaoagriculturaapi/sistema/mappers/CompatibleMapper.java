package br.com.smart4.gestaoagriculturaapi.sistema.mappers;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Compatible;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.CompatibleResponse;

public class CompatibleMapper {

    public static CompatibleResponse toResponse(Compatible entity) {
        if (entity == null) return null;

        return CompatibleResponse.builder()
                .id(entity.getId())
                .codSistema(entity.getCodSistema())
                .versaoLiberada(entity.getVersaoLiberada())
                .build();
    }
}

