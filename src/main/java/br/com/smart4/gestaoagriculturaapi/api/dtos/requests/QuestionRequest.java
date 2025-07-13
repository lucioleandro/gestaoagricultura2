package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.QuestionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {

    @NotBlank(message = "Description must not be blank.")
    private String descricao;

    @NotNull(message = "Active field must not be null.")
    private Boolean ativa;

    @NotNull(message = "Required field must not be null.")
    private Boolean obrigatoria;

    @NotNull(message = "Question type must not be null.")
    private QuestionTypeEnum tipoQuestion;
}

