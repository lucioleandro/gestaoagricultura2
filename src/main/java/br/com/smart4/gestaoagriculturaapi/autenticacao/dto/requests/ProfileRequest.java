package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.ProfileTypeEnum;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.SistemasMBEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private String nome;
    private String descricao;
    private ProfileTypeEnum tipo;
    private SistemasMBEnum sistema;
}
