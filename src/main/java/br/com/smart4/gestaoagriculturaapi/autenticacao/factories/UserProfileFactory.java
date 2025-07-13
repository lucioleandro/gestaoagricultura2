package br.com.smart4.gestaoagriculturaapi.autenticacao.factories;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserProfileRequest;

public final class UserProfileFactory {

    private UserProfileFactory() {}

    public static UserProfile fromRequest(UserProfileRequest request) {
        return UserProfile.builder()
                .ativo(request.isAtivo())
                .administrador(request.isAdministrador())
                .perfil(Profile.builder().id(request.getPerfilId()).build())
                .usuario(User.builder().id(request.getUsuarioId()).build())
                .build();
    }
}

