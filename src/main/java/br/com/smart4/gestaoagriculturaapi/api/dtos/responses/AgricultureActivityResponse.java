package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.IrrigationMethodEnum;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.WaterSourceEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgricultureActivityResponse {

    private Long id;
    private LocalDate dataPlantio;
    private String variedade;
    private double areaPlantada;
    private Integer quantidadePlantas;
    private double producaoAnual;
    private IrrigationMethodEnum metodoIrrigacao;
    private WaterSourceEnum fonteAgua;

    private Long propertyId;
    private String propertyNome;

    private Long productId;
    private String productDescricao;
}

