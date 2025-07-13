package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.QuestionRequest;

public final class QuestionFactory {

    private QuestionFactory() {}

    public static Question fromRequest(QuestionRequest request) {
        return Question.builder()
                .descricao(request.getDescricao())
                .ativa(request.getAtiva())
                .obrigatoria(request.getObrigatoria())
                .tipoQuestion(request.getTipoQuestion())
                .build();
    }
}

