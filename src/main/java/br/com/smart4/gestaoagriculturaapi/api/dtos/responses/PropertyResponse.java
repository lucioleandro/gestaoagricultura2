package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.LandRegularizationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {
    private Long id;
    private String nome;
    private String itr;
    private String incra;
    private String latitude;
    private String longitude;
    private Double areaTotal;
    private Double areaAgricola;
    private Double reservaLegal;
    private String tipoResidencia;
    private LandRegularizationEnum regularizacaoFundiaria;
    private Long farmerId;
    private Long addressId;
}
