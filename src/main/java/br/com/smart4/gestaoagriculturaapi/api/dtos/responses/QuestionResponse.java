package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.QuestionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String descricao;
    private Boolean ativa;
    private Boolean obrigatoria;
    private QuestionTypeEnum tipoQuestion;
}

