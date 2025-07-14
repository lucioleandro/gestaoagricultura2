package br.com.smart4.gestaoagriculturaapi.autenticacao.mappers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserProfileResponse;

import java.util.List;

public class UserProfileMapper {

    private UserProfileMapper() {}

    public static UserProfileResponse toResponse(UserProfile entity) {
        if (entity == null) return null;

        return UserProfileResponse.builder()
                .id(entity.getId())
                .ativo(entity.isAtivo())
                .administrador(entity.isAdministrador())

                .perfilId(entity.getPerfil() != null ? entity.getPerfil().getId() : null)
                .perfilNome(entity.getPerfil() != null ? entity.getPerfil().getNome() : null)
                .perfilDescricao(entity.getPerfil() != null ? entity.getPerfil().getDescricao() : null)
                .perfilTipo(entity.getPerfil() != null ? entity.getPerfil().getTipo() : null)
                .perfilSistema(entity.getPerfil() != null ? entity.getPerfil().getSistema() : null)

                .usuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .usuarioLogin(entity.getUsuario() != null ? entity.getUsuario().getLogin() : null)
                .usuarioNome(entity.getUsuario() != null ? entity.getUsuario().getNome() : null)

                .build();
    }

    public static List<UserProfileResponse> toListResponse(List<UserProfile> entities) {
        return entities.stream()
                .map(UserProfileMapper::toResponse)
                .toList();
    }

}

