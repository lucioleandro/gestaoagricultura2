package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.NeighborhoodRequest;

public final class NeighborhoodFactory {

    private NeighborhoodFactory() {}

    public static Neighborhood fromRequest(NeighborhoodRequest request) {
        return Neighborhood.builder()
                .nome(request.getNome())
                .zona(request.getZona())
                .city(City.builder().id(request.getCityId()).build())
                .build();
    }
}

