package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.CityResponse;

import java.util.List;

public class CityMapper {

    private CityMapper() {
    }

    public static CityResponse toResponse(City city) {
        if (city == null) return null;

        return CityResponse.builder()
                .id(city.getId())
                .nome(city.getNome())
                .uf(city.getUf())
                .cadastroUnico(city.getCadastroUnico())
                .build();
    }

    public static List<CityResponse> toListResponse(List<City> cities) {
        return cities.stream()
                .map(CityMapper::toResponse)
                .toList();
    }

}

