package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.CityRequest;

public final class CityFactory {

    private CityFactory() {}

    public static City fromRequest(CityRequest request) {
        return City.builder()
                .nome(request.getNome())
                .cadastroUnico(request.getCadastroUnico())
                .uf(request.getUf())
                .build();
    }
}

