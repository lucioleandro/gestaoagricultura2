package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Benefit;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.BenefitRequest;

public final class BenefitFactory {

    private BenefitFactory() {}

    public static Benefit fromRequest(BenefitRequest request) {
        return Benefit.builder()
                .descricao(request.getDescricao())
                .dataConcedimento(request.getDataConcedimento())
                .beneficiado(Farmer.builder().id(request.getBeneficiadoId()).build())
                .build();
    }
}

