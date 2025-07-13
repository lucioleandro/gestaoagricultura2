package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FarmerRequest {

    @NotBlank(message = "Name is required.")
    private String nome;

    @NotBlank(message = "CPF is required.")
    @Pattern(regexp = "\\d{11}", message = "CPF must have exactly 11 digits.")
    private String cpf;

    @NotBlank(message = "RG is required.")
    private String rg;

    private String orgaoExpeditor;

    private String apelido;

    @NotNull(message = "Birth date is required.")
    @Past(message = "Birth date must be in the past.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
}

