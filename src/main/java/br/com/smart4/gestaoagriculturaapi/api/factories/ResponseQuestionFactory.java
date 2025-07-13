package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.domains.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;

public final class ResponseQuestionFactory {

    private ResponseQuestionFactory() {}

    public static QuestionResponse fromRequest(ResponseQuestionRequest request) {
        return QuestionResponse.builder()
                .descricao(request.getDescricao())
                .question(Question.builder().id(request.getQuestionId()).build())
                .farmer(Farmer.builder().id(request.getFarmerId()).build())
                .build();
    }
}

