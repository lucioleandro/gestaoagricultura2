package br.com.smart4.gestaoagriculturaapi.autenticacao.factories;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.PermissionRequest;

public final class PermissionFactory {

    private PermissionFactory() {}

    public static Permission fromRequest(PermissionRequest request) {
        return Permission.builder()
                .componente(request.getComponente())
                .ativo(request.isAtivo())
                .atalho(request.isAtalho())
                .somenteLeitura(request.isSomenteLeitura())
                .perfil(Profile.builder().id(request.getPerfilId()).build())
                .build();
    }
}
