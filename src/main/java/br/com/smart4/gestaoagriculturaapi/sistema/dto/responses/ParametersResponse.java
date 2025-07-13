package br.com.smart4.gestaoagriculturaapi.sistema.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParametersResponse {
    private Long id;
    private String nome;
    private String cidade;
    private String uf;
    private String telefone;
    private String cnpj;
    private String cep;
    private String logradouro;
    private String numero;
    private String neighborhood;
    private String fax;
    private String tipoLogradouro;
    private String acod;
    private String codfebraban;
    private Integer codigoPM;
    private String inscestadual;
    private String inscmunicipal;
    private String ordenadorPrincipal;
}
