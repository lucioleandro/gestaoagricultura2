package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Benefit;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.BenefitResponse;

import java.util.List;

public class BenefitMapper {

    private BenefitMapper() {}

    public static BenefitResponse toResponse(Benefit benefit) {
        if (benefit == null) {
            return null;
        }

        return BenefitResponse.builder()
                .id(benefit.getId())
                .descricao(benefit.getDescricao())
                .dataConcedimento(benefit.getDataConcedimento())
                .beneficiadoId(benefit.getBeneficiado() != null ? benefit.getBeneficiado().getId() : null)
                .beneficiadoNome(benefit.getBeneficiado() != null ? benefit.getBeneficiado().getNome() : null)
                .build();
    }

    public static List<BenefitResponse> toListResponse(List<Benefit> benefits) {
        return benefits.stream()
                .map(BenefitMapper::toResponse)
                .toList();
    }

}

