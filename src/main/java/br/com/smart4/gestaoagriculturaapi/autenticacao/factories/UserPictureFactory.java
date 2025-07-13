package br.com.smart4.gestaoagriculturaapi.autenticacao.factories;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserPictureRequest;

public final class UserPictureFactory {

    private UserPictureFactory() {}

    public static UserPicture fromRequest(UserPictureRequest request) {
        return UserPicture.builder()
                .fotoPerfil(request.getFotoPerfil())
                .usuario(User.builder().id(request.getUserId()).build())
                .build();
    }
}

