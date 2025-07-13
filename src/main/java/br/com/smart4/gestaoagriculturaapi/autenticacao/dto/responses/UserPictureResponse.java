package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPictureResponse {
    private Long id;
    private String fotoPerfil;
    private Long userId;
    private String userLogin;
}

