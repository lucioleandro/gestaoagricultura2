package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardResponseRequest {

    @NotBlank(message = "Descrição não pode estar em branco.")
    private String descricao;

    @NotNull(message = "O ID da pergunta (questionId) é obrigatório.")
    private Long questionId;
}
