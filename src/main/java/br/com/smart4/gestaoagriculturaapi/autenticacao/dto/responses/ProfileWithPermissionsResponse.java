package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.SistemasMBEnum;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.ProfileTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileWithPermissionsResponse {
    private Long id;
    private String nome;
    private String descricao;
    private ProfileTypeEnum tipo;
    private SistemasMBEnum sistema;
    private List<PermissionResponse> permissoes;
}

