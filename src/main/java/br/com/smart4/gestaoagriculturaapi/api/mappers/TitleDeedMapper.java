package br.com.smart4.gestaoagriculturaapi.api.mappers;


import br.com.smart4.gestaoagriculturaapi.api.domains.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.TitleDeedResponse;

public class TitleDeedMapper {

    public static TitleDeedResponse toResponse(TitleDeed entity) {
        if (entity == null) return null;

        return TitleDeedResponse.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .observacao(entity.getObservacao())
                .dataAdicao(entity.getDataAdicao())
                .extensao(entity.getExtensao())
                .documento(entity.getDocumento())
                .propertyId(entity.getProperty() != null ? entity.getProperty().getId() : null)
                .propertyNome(entity.getProperty() != null ? entity.getProperty().getNome() : null)
                .build();
    }
}

