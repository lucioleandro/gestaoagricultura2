package br.com.smart4.gestaoagriculturaapi.sistema.mappers;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Parameters;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.ParametersResponse;

public class ParametersMapper {

    public static ParametersResponse toResponse(Parameters entity) {
        if (entity == null) return null;

        return ParametersResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cidade(entity.getCidade())
                .uf(entity.getUf())
                .telefone(entity.getTelefone())
                .cnpj(entity.getCnpj())
                .cep(entity.getCep())
                .logradouro(entity.getLogradouro())
                .numero(entity.getNumero())
                .neighborhood(entity.getNeighborhood())
                .fax(entity.getFax())
                .tipoLogradouro(entity.getTipoLogradouro())
                .acod(entity.getAcod())
                .codfebraban(entity.getCodfebraban())
                .codigoPM(entity.getCodigoPM())
                .inscestadual(entity.getInscestadual())
                .inscmunicipal(entity.getInscmunicipal())
                .ordenadorPrincipal(entity.getOrdenadorPrincipal())
                .build();
    }
}

