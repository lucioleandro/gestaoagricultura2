package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.FarmingSystem;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.FarmingSystemRequest;

public final class FarmingSystemFactory {

    private FarmingSystemFactory() {}

    public static FarmingSystem fromRequest(FarmingSystemRequest request) {
        return FarmingSystem.builder()
                .descricao(request.getDescricao())
                .ramoAtividade(request.getRamoAtividade())
                .build();
    }
}

