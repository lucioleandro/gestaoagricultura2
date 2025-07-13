package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomicActivityResponse {
    private Long id;
    private String codigocnae;
    private String observacao;
    private String descricao;
    private Boolean situacao;
    private Double aliquota;
    private Double valor;
    private boolean isentoiss;
    private Boolean atividadeDeServico;
}

