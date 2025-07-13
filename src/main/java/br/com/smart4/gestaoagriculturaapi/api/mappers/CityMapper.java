package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.CityResponse;

public class CityMapper {

    public static CityResponse toResponse(City city) {
        if (city == null) return null;

        return CityResponse.builder()
                .id(city.getId())
                .nome(city.getNome())
                .uf(city.getUf())
                .cadastroUnico(city.getCadastroUnico())
                .build();
    }
}

