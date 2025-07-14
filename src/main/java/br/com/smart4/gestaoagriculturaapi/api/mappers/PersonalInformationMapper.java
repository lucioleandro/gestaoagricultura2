package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PersonalInformationResponse;

import java.util.List;

public class PersonalInformationMapper {

    private PersonalInformationMapper() {}

    public static PersonalInformationResponse toResponse(PersonalInformation entity) {
        if (entity == null) return null;

        return PersonalInformationResponse.builder()
                .id(entity.getId())
                .apelido(entity.getApelido())
                .mae(entity.getMae())
                .pai(entity.getPai())
                .maritalStatus(entity.getMaritalStatus())
                .nomeConjugue(entity.getNomeConjugue())
                .farmerId(entity.getFarmer() != null ? entity.getFarmer().getId() : null)
                .farmerNome(entity.getFarmer() != null ? entity.getFarmer().getNome() : null)
                .build();
    }

    public static List<PersonalInformationResponse> toListResponse(List<PersonalInformation> entities) {
        return entities.stream()
                .map(PersonalInformationMapper::toResponse)
                .toList();
    }

}
