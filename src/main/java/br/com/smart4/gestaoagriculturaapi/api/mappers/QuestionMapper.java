package br.com.smart4.gestaoagriculturaapi.api.mappers;


import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.QuestionResponse;

import java.util.List;

public class QuestionMapper {

    private QuestionMapper() {
    }

    public static QuestionResponse toResponse(Question entity) {
        if (entity == null) return null;

        return QuestionResponse.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .ativa(entity.getAtiva())
                .obrigatoria(entity.getObrigatoria())
                .tipoQuestion(entity.getTipoQuestion())
                .build();
    }

    public static List<QuestionResponse> toListResponse(List<Question> entities) {
        return entities.stream()
                .map(QuestionMapper::toResponse)
                .toList();
    }

}

