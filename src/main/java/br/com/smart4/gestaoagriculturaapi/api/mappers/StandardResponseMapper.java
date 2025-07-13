package br.com.smart4.gestaoagriculturaapi.api.mappers;


import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.StandardResponseDTO;

public class StandardResponseMapper {

    public static StandardResponseDTO toResponse(StandardResponse entity) {
        if (entity == null) return null;

        return StandardResponseDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .questionId(entity.getQuestion() != null ? entity.getQuestion().getId() : null)
                .questionDescricao(entity.getQuestion() != null ? entity.getQuestion().getDescricao() : null)
                .build();
    }
}
