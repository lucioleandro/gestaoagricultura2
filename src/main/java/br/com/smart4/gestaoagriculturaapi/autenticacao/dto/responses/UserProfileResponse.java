package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.ProfileTypeEnum;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.SistemasMBEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private Long id;
    private boolean ativo;
    private boolean administrador;

    private Long perfilId;
    private String perfilNome;
    private String perfilDescricao;
    private ProfileTypeEnum perfilTipo;
    private SistemasMBEnum perfilSistema;

    private Long usuarioId;
    private String usuarioLogin;
    private String usuarioNome;
}

