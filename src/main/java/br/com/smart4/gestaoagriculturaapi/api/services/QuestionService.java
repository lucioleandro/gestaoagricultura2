package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.QuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.QuestionFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.QuestionMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.StandardResponseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository perguntaRepository;
    private final StandardResponseRepository standardResponseRepository;

    public QuestionService(QuestionRepository perguntaRepository, StandardResponseRepository standardResponseRepository) {
        this.perguntaRepository = perguntaRepository;
        this.standardResponseRepository = standardResponseRepository;
    }

    @Transactional
    public QuestionResponse create(QuestionRequest pergunta) {
        Question entity = perguntaRepository.save(
                QuestionFactory.fromRequest(pergunta)
        );
        return QuestionMapper.toResponse(entity);
    }

    @Transactional
    public QuestionResponse update(Long id, QuestionRequest pergunta) {
        Question existing = perguntaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));

        existing.setDescricao(pergunta.getDescricao());
        existing.setAtiva(pergunta.getAtiva());
        existing.setObrigatoria(pergunta.getObrigatoria());
        existing.setTipoQuestion(pergunta.getTipoQuestion());

        return QuestionMapper.toResponse(perguntaRepository.save(existing));
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
    public void remove(Long questionId) {
        Question question = perguntaRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));
        standardResponseRepository.deleteAllByQuestionId(questionId);
        perguntaRepository.delete(question);
    }
}
