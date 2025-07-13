package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {

    @NotBlank(message = "City name must not be blank.")
    private String nome;

    @NotNull(message = "Unique registry code (cadastroUnico) is required.")
    @Positive(message = "Unique registry code must be a positive number.")
    private Integer cadastroUnico;

    @NotBlank(message = "UF (federal unit) must not be blank.")
    @Size(min = 2, max = 2, message = "UF must have exactly 2 characters.")
    private String uf;
}

