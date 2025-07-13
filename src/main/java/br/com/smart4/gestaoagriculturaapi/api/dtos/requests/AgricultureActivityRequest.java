package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.IrrigationMethodEnum;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.WaterSourceEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgricultureActivityRequest {

    @PastOrPresent(message = "Planting date must be in the past or today.")
    private LocalDate dataPlantio;

    @Size(max = 255, message = "Variety must be at most 255 characters long.")
    private String variedade;

    @Positive(message = "Planted area must be greater than 0.")
    private double areaPlantada;

    @Min(value = 0, message = "Number of plants must not be negative.")
    private Integer quantidadePlantas;

    @Min(value = 0, message = "Annual production must not be negative.")
    private double producaoAnual;

    @NotNull(message = "Irrigation method is required.")
    private IrrigationMethodEnum metodoIrrigacao;

    @NotNull(message = "Water source is required.")
    private WaterSourceEnum fonteAgua;

    @NotNull(message = "Property ID is required.")
    private Long propertyId;

    @NotNull(message = "Product ID is required.")
    private Long productId;

}

