package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseQuestionRequest {

    @NotBlank(message = "Description must not be blank.")
    private String descricao;

    @NotNull(message = "Question ID must not be null.")
    private Long questionId;

    @NotNull(message = "Farmer ID must not be null.")
    private Long farmerId;
}

