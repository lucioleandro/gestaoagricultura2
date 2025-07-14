package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.StandardResponseRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.StandardResponseDTO;
import br.com.smart4.gestaoagriculturaapi.api.factories.StandardResponseFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.StandardResponseMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.StandardResponseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StandardResponseService {

    private final StandardResponseRepository repository;
    private final QuestionRepository questionRepository;

    public StandardResponseService(StandardResponseRepository respostaPadraoRepository,
                                   QuestionRepository questionRepository) {
        this.repository = respostaPadraoRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public StandardResponseDTO create(StandardResponseRequest respostaPadrao) {
        questionRepository.findById(respostaPadrao.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + respostaPadrao.getQuestionId()));

        StandardResponse saved = repository.save(
                StandardResponseFactory.fromRequest(respostaPadrao)
        );
        return StandardResponseMapper.toResponse(saved);
    }

    @Transactional
    public StandardResponseDTO update(Long id, StandardResponseRequest respostaPadrao) {
        StandardResponse existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StandardResponse not found with id: " + id));

        existing.setDescricao(respostaPadrao.getDescricao());

        if (!existing.getQuestion().getId().equals(respostaPadrao.getQuestionId())) {
            Question question = questionRepository.findById(respostaPadrao.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + respostaPadrao.getQuestionId()));
            existing.setQuestion(question);
        }

        return StandardResponseMapper.toResponse(repository.save(existing));
    }

    public Optional<StandardResponseDTO> findById(Long id) {
        return repository.findById(id)
                .map(StandardResponseMapper::toResponse);
    }

    public List<StandardResponseDTO> findAll() {
        return StandardResponseMapper.toListResponse(
                repository.findAll()
        );
    }

    public List<StandardResponseDTO> findByQuestionId(Long id) {
        return StandardResponseMapper.toListResponse(
                repository.findByQuestionId(id)
        );
    }

    @Transactional
    public void remove(Long standardResponseId) {
        StandardResponse standardResponse = repository.findById(standardResponseId)
                .orElseThrow(() -> new EntityNotFoundException("StandardResponse not found with id: " + standardResponseId));
        repository.delete(standardResponse);
    }
}
