package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    private Long id;
    private String componente;
    private boolean ativo;
    private boolean atalho;
    private boolean somenteLeitura;
    private Long perfilId;
    private String perfilDescricao;
}
