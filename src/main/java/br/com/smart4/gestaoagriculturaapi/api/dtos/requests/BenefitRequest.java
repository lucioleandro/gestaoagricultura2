package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BenefitRequest {

    @NotBlank(message = "Description must not be blank.")
    private String descricao;

    @NotNull(message = "Grant date is required.")
    private LocalDateTime dataConcedimento;

    @NotNull(message = "Farmer ID is required.")
    private Long beneficiadoId;
}

