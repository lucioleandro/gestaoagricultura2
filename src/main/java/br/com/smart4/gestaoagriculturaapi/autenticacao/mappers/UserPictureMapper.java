package br.com.smart4.gestaoagriculturaapi.autenticacao.mappers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserPictureResponse;

import java.util.List;

public class UserPictureMapper {

    private UserPictureMapper() {
    }

    public static UserPictureResponse toResponse(UserPicture entity) {
        if (entity == null) return null;

        return UserPictureResponse.builder()
                .id(entity.getId())
                .fotoPerfil(entity.getFotoPerfil())
                .userId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .userLogin(entity.getUsuario() != null ? entity.getUsuario().getLogin() : null)
                .build();
    }

    public static List<UserPictureResponse> toListResponse(List<UserPicture> entities) {
        return entities.stream()
                .map(UserPictureMapper::toResponse)
                .toList();
    }

}

