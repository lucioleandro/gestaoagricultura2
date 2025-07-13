package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.MaritalStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalInformationRequest {

    @NotBlank(message = "The nickname must not be blank.")
    private String apelido;

    @NotBlank(message = "Mother's name must not be blank.")
    private String mae;

    @NotBlank(message = "Father's name must not be blank.")
    private String pai;

    @NotNull(message = "Marital status must be provided.")
    private MaritalStatusEnum maritalStatusEnum;

    private String nomeConjugue;

    @NotNull(message = "Farmer ID must be provided.")
    private Long farmerId;
}

