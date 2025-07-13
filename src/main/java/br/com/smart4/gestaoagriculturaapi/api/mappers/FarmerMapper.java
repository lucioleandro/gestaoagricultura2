package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.FarmerResponse;

public class FarmerMapper {

    public static FarmerResponse toResponse(Farmer entity) {
        if (entity == null) return null;

        return FarmerResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .apelido(entity.getApelido())
                .cpf(entity.getCpf())
                .rg(entity.getRg())
                .orgaoExpeditor(entity.getOrgaoExpeditor())
                .dataNascimento(entity.getDataNascimento())
                .build();
    }
}

