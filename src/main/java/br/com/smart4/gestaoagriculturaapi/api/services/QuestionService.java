package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.QuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.QuestionFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.QuestionMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository perguntaRepository;

    public QuestionService(QuestionRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    @Transactional
    public QuestionResponse create(QuestionRequest pergunta) {
        Question entity = perguntaRepository.save(
                QuestionFactory.fromRequest(pergunta)
        );
        return QuestionMapper.toResponse(entity);
    }

    @Transactional
    public QuestionResponse update(QuestionRequest pergunta) {
        Question entity = perguntaRepository.save(
                QuestionFactory.fromRequest(pergunta)
        );
        return QuestionMapper.toResponse(entity);
    }

    public Optional<QuestionResponse> findById(Long id) {
        return perguntaRepository.findById(id)
                .map(QuestionMapper::toResponse);
    }

    public List<QuestionResponse> findAll() {
        return QuestionMapper.toListResponse(
                perguntaRepository.findAll()
        );
    }

    public List<QuestionResponse> findQuestionsAtivas() {
        return QuestionMapper.toListResponse(
                perguntaRepository.findQuestionsAtivas()
        );
    }

    @Transactional
    public void remove(Question pergunta) {
        perguntaRepository.delete(pergunta);
    }
}
