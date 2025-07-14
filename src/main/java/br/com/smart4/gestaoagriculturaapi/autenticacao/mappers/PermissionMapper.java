package br.com.smart4.gestaoagriculturaapi.autenticacao.mappers;


import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.PermissionResponse;

import java.util.List;

public class PermissionMapper {

    private PermissionMapper() {
    }

    public static PermissionResponse toResponse(Permission entity) {
        if (entity == null) return null;

        return PermissionResponse.builder()
                .id(entity.getId())
                .componente(entity.getComponente())
                .ativo(entity.isAtivo())
                .atalho(entity.isAtalho())
                .somenteLeitura(entity.isSomenteLeitura())
                .perfilId(entity.getPerfil() != null ? entity.getPerfil().getId() : null)
                .perfilDescricao(entity.getPerfil() != null ? entity.getPerfil().getDescricao() : null)
                .build();
    }

    public static List<PermissionResponse> toListResponse(List<Permission> entities) {
        return entities.stream()
                .map(PermissionMapper::toResponse)
                .toList();
    }

}

