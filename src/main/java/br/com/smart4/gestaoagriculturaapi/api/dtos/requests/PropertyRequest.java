package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.LandRegularizationEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyRequest {

    @NotBlank(message = "Property name must not be blank.")
    private String nome;

    private String itr;

    private String incra;

    @NotBlank(message = "Latitude must not be blank.")
    private String latitude;

    @NotBlank(message = "Longitude must not be blank.")
    private String longitude;

    private Double areaTotal;

    private Double areaAgricola;

    private Double reservaLegal;

    private String tipoResidencia;

    private LandRegularizationEnum regularizacaoFundiaria;

    @NotNull(message = "Farmer ID must not be null.")
    private Long farmerId;

    @NotNull(message = "Address ID must not be null.")
    private Long addressId;

    private AddressRequest address;
}

