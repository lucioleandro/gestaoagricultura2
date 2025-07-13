package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String descricao;
    private String unidadeMedida;
    private String siglaUnidadeMedida;
}

