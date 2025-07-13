package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequest {
    private boolean ativo;
    private boolean administrador;
    private Long perfilId;
    private Long usuarioId;
}

