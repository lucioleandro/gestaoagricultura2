package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NeighborhoodRequest {

    @NotBlank(message = "The neighborhood name must not be blank.")
    private String nome;

    @NotBlank(message = "The zone must not be blank.")
    private String zona;

    @NotNull(message = "The city ID must be provided.")
    private Long cityId;
}

