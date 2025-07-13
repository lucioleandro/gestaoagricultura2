package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Related to QuestionResponse Entity

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnsweredQuestionResponse {
    private Long id;
    private String descricao;
    private Long questionId;
    private String questionDescricao;
    private Long farmerId;
    private String farmerNome;
}

