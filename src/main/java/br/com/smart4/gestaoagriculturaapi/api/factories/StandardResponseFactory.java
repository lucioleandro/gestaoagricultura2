package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.StandardResponseRequest;

public final class StandardResponseFactory {

    private StandardResponseFactory() {}

    public static StandardResponse fromRequest(StandardResponseRequest request) {
        return StandardResponse.builder()
                .descricao(request.getDescricao())
                .question(Question.builder().id(request.getQuestionId()).build())
                .build();
    }
}

