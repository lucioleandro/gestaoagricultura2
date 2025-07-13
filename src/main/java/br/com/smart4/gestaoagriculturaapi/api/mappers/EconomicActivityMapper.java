package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityResponse;

public class EconomicActivityMapper {

    public static EconomicActivityResponse toResponse(EconomicActivity entity) {
        if (entity == null) return null;

        return EconomicActivityResponse.builder()
                .id(entity.getId())
                .codigocnae(entity.getCodigocnae())
                .observacao(entity.getObservacao())
                .descricao(entity.getDescricao())
                .situacao(entity.getSituacao())
                .aliquota(entity.getAliquota())
                .valor(entity.getValor())
                .isentoiss(entity.isIsentoiss())
                .atividadeDeServico(entity.getAtividadeDeServico())
                .build();
    }
}
