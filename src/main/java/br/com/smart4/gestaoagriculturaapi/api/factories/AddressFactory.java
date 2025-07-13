package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;

public class AddressFactory {

    private AddressFactory() {
    }

    public static Address fromRequest(AddressRequest request) {
        return Address.builder()
                .logradouro(request.getLogradouro())
                .numero(request.getNumero())
                .cep(request.getCep())
                .complemento(request.getComplemento())
                .tipoLogradouro(request.getTipoLogradouro())
                .city(City.builder().id(request.getCityId()).build())
                .neighborhood(Neighborhood.builder().id(request.getNeighborhoodId()).build())
                .build();
    }
}
