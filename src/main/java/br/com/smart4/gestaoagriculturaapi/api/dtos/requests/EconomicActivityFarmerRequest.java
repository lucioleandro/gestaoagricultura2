package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EconomicActivityFarmerRequest {

    @NotNull(message = "Main activity flag (principal) must be provided.")
    private Boolean principal;

    @NotNull(message = "Start date is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicial;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFinal;

    @NotNull(message = "Farmer ID is required.")
    private Long farmerId;

    @NotNull(message = "Property ID is required.")
    private Long propertyId;

    @NotNull(message = "Economic activity ID is required.")
    private Long economicActivityId;
}

