package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponseDTO {
    private Long id;
    private String descricao;
    private Long questionId;
    private String questionDescricao;
}

