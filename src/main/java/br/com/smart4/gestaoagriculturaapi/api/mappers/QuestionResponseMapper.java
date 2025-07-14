package br.com.smart4.gestaoagriculturaapi.api.mappers;


import br.com.smart4.gestaoagriculturaapi.api.domains.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AnsweredQuestionResponse;

import java.util.List;

public class QuestionResponseMapper {

    private QuestionResponseMapper() {}

    public static AnsweredQuestionResponse toResponse(QuestionResponse entity) {
        if (entity == null) return null;

        return AnsweredQuestionResponse.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .questionId(entity.getQuestion() != null ? entity.getQuestion().getId() : null)
                .questionDescricao(entity.getQuestion() != null ? entity.getQuestion().getDescricao() : null)
                .farmerId(entity.getFarmer() != null ? entity.getFarmer().getId() : null)
                .farmerNome(entity.getFarmer() != null ? entity.getFarmer().getNome() : null)
                .build();
    }

    public static List<AnsweredQuestionResponse> toListResponse(List<QuestionResponse> entities) {
        return entities.stream()
                .map(QuestionResponseMapper::toResponse)
                .toList();
    }

}

