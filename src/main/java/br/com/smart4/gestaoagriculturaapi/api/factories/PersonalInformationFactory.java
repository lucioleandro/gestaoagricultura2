package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PersonalInformationRequest;

public final class PersonalInformationFactory {

    private PersonalInformationFactory() {}

    public static PersonalInformation fromRequest(PersonalInformationRequest request) {
        return PersonalInformation.builder()
                .apelido(request.getApelido())
                .mae(request.getMae())
                .pai(request.getPai())
                .maritalStatus(request.getMaritalStatus())
                .nomeConjugue(request.getNomeConjugue())
                .farmer(Farmer.builder().id(request.getFarmerId()).build())
                .build();
    }
}

