package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionRequest {
    private String componente;
    private boolean ativo;
    private boolean atalho;
    private boolean somenteLeitura;
    private Long perfilId;
}

