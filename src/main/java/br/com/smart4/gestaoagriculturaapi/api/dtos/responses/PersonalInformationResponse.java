package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.MaritalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformationResponse {
    private Long id;
    private String apelido;
    private String mae;
    private String pai;
    private MaritalStatusEnum maritalStatus;
    private String nomeConjugue;

    private Long farmerId;
    private String farmerNome;
}

