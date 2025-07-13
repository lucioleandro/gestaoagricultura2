package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.SpeciesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivestockActivityResponse {
    private Long id;
    private SpeciesEnum especie;
    private String raca;
    private Integer quantidade;
    private Long propertyId;
}

