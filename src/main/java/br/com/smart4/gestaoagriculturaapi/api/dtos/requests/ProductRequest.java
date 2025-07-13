package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "Description must not be blank.")
    private String descricao;

    @NotBlank(message = "Unit of measurement must not be blank.")
    private String unidadeMedida;

    @NotBlank(message = "Measurement unit abbreviation must not be blank.")
    private String siglaUnidadeMedida;
}

