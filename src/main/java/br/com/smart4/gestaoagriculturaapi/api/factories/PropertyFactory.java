package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PropertyRequest;

public final class PropertyFactory {

    private PropertyFactory() {}

    public static Property fromRequest(PropertyRequest request) {
        return Property.builder()
                .nome(request.getNome())
                .itr(request.getItr())
                .incra(request.getIncra())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .areaTotal(request.getAreaTotal())
                .areaAgricola(request.getAreaAgricola())
                .reservaLegal(request.getReservaLegal())
                .tipoResidencia(request.getTipoResidencia())
                .regularizacaoFundiaria(request.getRegularizacaoFundiaria())
                .farmer(Farmer.builder().id(request.getFarmerId()).build())
                .address(Address.builder().id(request.getAddressId()).build())
                .build();
    }
}
