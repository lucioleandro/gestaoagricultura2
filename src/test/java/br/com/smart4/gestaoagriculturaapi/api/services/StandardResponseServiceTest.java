package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.StandardResponseRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.StandardResponseDTO;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.StandardResponseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StandardResponseServiceTest {

    @Mock
    private StandardResponseRepository repository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private StandardResponseService service;

    private StandardResponseRequest request;
    private StandardResponse entity;
    private Question question;
    private Question newQuestion;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setId(1L);
        newQuestion = new Question();
        newQuestion.setId(2L);

        request = new StandardResponseRequest();
        request.setDescricao("Resposta padrao");
        request.setQuestionId(question.getId());

        entity = new StandardResponse();
        entity.setId(10L);
        entity.setDescricao(request.getDescricao());
        entity.setQuestion(question);
    }

    @Test
    void testCreateSuccess() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        when(repository.save(any(StandardResponse.class))).thenReturn(entity);

        StandardResponseDTO response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDescricao(), response.getDescricao());
        assertEquals(question.getId(), response.getQuestionId());
        verify(questionRepository).findById(question.getId());
        verify(repository).save(any(StandardResponse.class));
    }

    @Test
    void testCreateQuestionNotFound() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.create(request));
        assertTrue(ex.getMessage().contains("Question not found with id"));
    }

    @Test
    void testUpdateSuccessNoQuestionChange() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        // request.questionId equals existing.question.id
        request.setQuestionId(question.getId());
        when(repository.save(any(StandardResponse.class))).thenReturn(entity);

        StandardResponseDTO response = service.update(entity.getId(), request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDescricao(), response.getDescricao());
        // no new question lookup
        verify(repository).findById(entity.getId());
        verify(repository).save(any(StandardResponse.class));
        verify(questionRepository, never()).findById(anyLong());
    }

    @Test
    void testUpdateSuccessWithQuestionChange() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        // change question id
        request.setQuestionId(newQuestion.getId());
        when(questionRepository.findById(newQuestion.getId())).thenReturn(Optional.of(newQuestion));
        StandardResponse updated = new StandardResponse();
        updated.setId(entity.getId());
        updated.setDescricao(request.getDescricao());
        updated.setQuestion(newQuestion);
        when(repository.save(any(StandardResponse.class))).thenReturn(updated);

        StandardResponseDTO response = service.update(entity.getId(), request);

        assertNotNull(response);
        assertEquals(updated.getId(), response.getId());
        assertEquals(newQuestion.getId(), response.getQuestionId());
        verify(questionRepository).findById(newQuestion.getId());
        verify(repository).save(any(StandardResponse.class));
    }

    @Test
    void testUpdateNotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("StandardResponse not found with id"));
    }

    @Test
    void testUpdateQuestionNotFound() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        request.setQuestionId(newQuestion.getId());
        when(questionRepository.findById(newQuestion.getId())).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Question not found with id"));
    }

    @Test
    void testFindByIdExists() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        Optional<StandardResponseDTO> opt = service.findById(entity.getId());
        assertTrue(opt.isPresent());
        assertEquals(entity.getId(), opt.get().getId());
    }

    @Test
    void testFindByIdNotExists() {
        when(repository.findById(123L)).thenReturn(Optional.empty());
        Optional<StandardResponseDTO> opt = service.findById(123L);
        assertTrue(opt.isEmpty());
    }

    @Test
    void testFindAll() {
        List<StandardResponse> list = Arrays.asList(entity);
        when(repository.findAll()).thenReturn(list);
        List<StandardResponseDTO> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByQuestionId() {
        List<StandardResponse> list = Arrays.asList(entity);
        when(repository.findByQuestionId(question.getId())).thenReturn(list);
        List<StandardResponseDTO> responses = service.findByQuestionId(question.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemove() {
        service.remove(entity);
        verify(repository).delete(entity);
    }
}

