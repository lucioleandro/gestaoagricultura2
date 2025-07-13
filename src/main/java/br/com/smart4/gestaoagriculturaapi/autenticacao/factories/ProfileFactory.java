package br.com.smart4.gestaoagriculturaapi.autenticacao.factories;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.ProfileRequest;

public final class ProfileFactory {

    private ProfileFactory() {}

    public static Profile fromRequest(ProfileRequest request) {
        return Profile.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .tipo(request.getTipo())
                .sistema(request.getSistema())
                .build();
    }
}

