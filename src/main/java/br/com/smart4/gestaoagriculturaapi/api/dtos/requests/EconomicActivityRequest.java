package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EconomicActivityRequest {

    @NotBlank(message = "CNAE code must not be blank.")
    @Size(max = 15, message = "CNAE code must be at most 15 characters.")
    private String codigocnae;

    @Size(max = 500, message = "Observation must be at most 500 characters.")
    private String observacao;

    @NotBlank(message = "Description must not be blank.")
    private String descricao;

    @NotNull(message = "Status must be provided.")
    private Boolean situacao;

    @DecimalMin(value = "0.0", inclusive = true, message = "Tax rate must be a positive number.")
    private Double aliquota;

    @DecimalMin(value = "0.0", inclusive = true, message = "Value must be a positive number.")
    private Double valor;

    private boolean isentoiss;

    @NotNull(message = "Service activity flag must be provided.")
    private Boolean atividadeDeServico;
}

