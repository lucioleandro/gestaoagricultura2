package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.SpeciesEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivestockActivityRequest {

    @NotNull(message = "The species must be provided.")
    private SpeciesEnum especie;

    @NotNull(message = "The quantity must be provided.")
    @Min(value = 1, message = "The quantity must be at least 1.")
    private Integer quantidade;

    @NotBlank(message = "The breed must not be blank.")
    private String raca;

    @NotNull(message = "The property ID must be provided.")
    private Long propertyId;
}

