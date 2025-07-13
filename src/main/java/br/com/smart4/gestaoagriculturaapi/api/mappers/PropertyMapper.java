package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PropertyResponse;

public class PropertyMapper {

    public static PropertyResponse toResponse(Property entity) {
        if (entity == null) return null;

        return PropertyResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .itr(entity.getItr())
                .incra(entity.getIncra())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .areaTotal(entity.getAreaTotal())
                .areaAgricola(entity.getAreaAgricola())
                .reservaLegal(entity.getReservaLegal())
                .tipoResidencia(entity.getTipoResidencia())
                .regularizacaoFundiaria(entity.getRegularizacaoFundiaria())
                .farmerId(entity.getFarmer() != null ? entity.getFarmer().getId() : null)
                .addressId(entity.getAddress() != null ? entity.getAddress().getId() : null)
                .build();
    }
}

