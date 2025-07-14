package br.com.smart4.gestaoagriculturaapi.api.mappers;


import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.StandardResponseDTO;

import java.util.List;

public class StandardResponseMapper {

    private StandardResponseMapper() {}

    public static StandardResponseDTO toResponse(StandardResponse entity) {
        if (entity == null) return null;

        return StandardResponseDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .questionId(entity.getQuestion() != null ? entity.getQuestion().getId() : null)
                .questionDescricao(entity.getQuestion() != null ? entity.getQuestion().getDescricao() : null)
                .build();
    }

    public static List<StandardResponseDTO> toListResponse(List<StandardResponse> entities) {
        return entities.stream()
                .map(StandardResponseMapper::toResponse)
                .toList();
    }

}
