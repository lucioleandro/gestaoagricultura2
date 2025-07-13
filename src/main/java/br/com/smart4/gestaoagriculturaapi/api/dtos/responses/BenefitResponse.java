package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BenefitResponse {

    private Long id;
    private String descricao;
    private LocalDateTime dataConcedimento;

    private Long beneficiadoId;
    private String beneficiadoNome;
}

