package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.FarmerRequest;

public final class FarmerFactory {

    private FarmerFactory() {}

    public static Farmer fromRequest(FarmerRequest request) {
        return Farmer.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .rg(request.getRg())
                .orgaoExpeditor(request.getOrgaoExpeditor())
                .apelido(request.getApelido())
                .dataNascimento(request.getDataNascimento())
                .build();
    }
}

