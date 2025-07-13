package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPictureRequest {
    private String fotoPerfil;
    private Long userId;
}

