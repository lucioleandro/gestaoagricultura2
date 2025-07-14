package br.com.smart4.gestaoagriculturaapi.api.mappers;

import br.com.smart4.gestaoagriculturaapi.api.domains.Address;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AddressResponse;

import java.util.List;

public class AddressMapper {

    private AddressMapper() {
    }

    public static AddressResponse toResponse(Address address) {
        if (address == null) {
            return null;
        }

        return AddressResponse.builder()
                .id(address.getId())
                .logradouro(address.getLogradouro())
                .numero(address.getNumero())
                .cep(address.getCep())
                .complemento(address.getComplemento())
                .tipoLogradouro(address.getTipoLogradouro())
                .cityId(address.getCity() != null ? address.getCity().getId() : null)
                .cityName(address.getCity() != null ? address.getCity().getNome() : null)
                .neighborhoodId(address.getNeighborhood() != null ? address.getNeighborhood().getId() : null)
                .neighborhoodName(address.getNeighborhood() != null ? address.getNeighborhood().getNome() : null)
                .build();
    }

    public static List<AddressResponse> toListResponse(List<Address> addresses) {
        return addresses.stream()
                .map(AddressMapper::toResponse)
                .toList();
    }
}

