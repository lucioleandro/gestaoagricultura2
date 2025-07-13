package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityRequest;

public final class EconomicActivityFactory {

    private EconomicActivityFactory() {}

    public static EconomicActivity fromRequest(EconomicActivityRequest request) {
        return EconomicActivity.builder()
                .codigocnae(request.getCodigocnae())
                .observacao(request.getObservacao())
                .descricao(request.getDescricao())
                .situacao(request.getSituacao())
                .aliquota(request.getAliquota())
                .valor(request.getValor())
                .isentoiss(request.isIsentoiss())
                .atividadeDeServico(request.getAtividadeDeServico())
                .build();
    }
}

