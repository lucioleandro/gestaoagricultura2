package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.FarmSystemEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmingSystemRequest {

    @NotBlank(message = "The description must not be blank.")
    private String descricao;

    @NotNull(message = "The farm system type must be provided.")
    private FarmSystemEnum ramoAtividade;
}

