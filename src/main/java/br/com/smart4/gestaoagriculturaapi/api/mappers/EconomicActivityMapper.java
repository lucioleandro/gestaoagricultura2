package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityResponse;

import java.util.List;

public class EconomicActivityMapper {

    private EconomicActivityMapper() {
    }

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

    public static List<EconomicActivityResponse> toListResponse(List<EconomicActivity> entities) {
        return entities.stream()
                .map(EconomicActivityMapper::toResponse)
                .toList();
    }

}
