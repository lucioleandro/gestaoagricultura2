package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.StreetTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;
    private String logradouro;
    private String numero;
    private String cep;
    private String complemento;
    private StreetTypeEnum tipoLogradouro;

    private Long cityId;
    private String cityName;

    private Long neighborhoodId;
    private String neighborhoodName;
}

