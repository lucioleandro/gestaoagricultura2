package br.com.smart4.gestaoagriculturaapi.autenticacao.mappers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserPictureResponse;

public class UserPictureMapper {

    public static UserPictureResponse toResponse(UserPicture entity) {
        if (entity == null) return null;

        return UserPictureResponse.builder()
                .id(entity.getId())
                .fotoPerfil(entity.getFotoPerfil())
                .userId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .userLogin(entity.getUsuario() != null ? entity.getUsuario().getLogin() : null)
                .build();
    }
}

