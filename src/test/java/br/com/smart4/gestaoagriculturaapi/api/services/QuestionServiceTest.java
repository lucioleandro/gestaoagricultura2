package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.QuestionTypeEnum;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.QuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionRepository;
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
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService service;

    private QuestionRequest request;
    private Question entity;

    @BeforeEach
    void setUp() {
        request = new QuestionRequest();
        request.setDescricao("Descricao pergunta");
        request.setAtiva(true);
        request.setObrigatoria(false);
        request.setTipoQuestion(QuestionTypeEnum.A);

        entity = new Question();
        entity.setId(123L);
        entity.setDescricao(request.getDescricao());
        entity.setAtiva(request.getAtiva());
        entity.setObrigatoria(request.getObrigatoria());
        entity.setTipoQuestion(request.getTipoQuestion());
    }

    @Test
    void testCreate() {
        when(questionRepository.save(any(Question.class))).thenReturn(entity);

        QuestionResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDescricao(), response.getDescricao());
        assertTrue(response.getAtiva());
        assertFalse(response.getObrigatoria());
        assertEquals(request.getTipoQuestion(), response.getTipoQuestion());
        verify(questionRepository).save(any(Question.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(questionRepository.findById(id)).thenReturn(Optional.of(entity));
        when(questionRepository.save(any(Question.class))).thenReturn(entity);

        QuestionRequest updateReq = new QuestionRequest();
        updateReq.setDescricao("Nova descricao");
        updateReq.setAtiva(false);
        updateReq.setObrigatoria(true);
        updateReq.setTipoQuestion(QuestionTypeEnum.ME);

        QuestionResponse response = service.update(id, updateReq);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Nova descricao", response.getDescricao());
        assertFalse(response.getAtiva());
        assertTrue(response.getObrigatoria());
        assertEquals(QuestionTypeEnum.ME, response.getTipoQuestion());
        verify(questionRepository).findById(id);
        verify(questionRepository).save(any(Question.class));
    }

    @Test
    void testUpdateNotFound() {
        when(questionRepository.findById(999L)).thenReturn(Optional.empty());
        QuestionRequest updateReq = new QuestionRequest();
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, updateReq));
        assertTrue(ex.getMessage().contains("Question not found with id"));
    }

    @Test
    void testFindByIdExists() {
        when(questionRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        Optional<QuestionResponse> opt = service.findById(entity.getId());
        assertTrue(opt.isPresent());
        assertEquals(entity.getId(), opt.get().getId());
    }

    @Test
    void testFindByIdNotExists() {
        when(questionRepository.findById(555L)).thenReturn(Optional.empty());
        Optional<QuestionResponse> opt = service.findById(555L);
        assertTrue(opt.isEmpty());
    }

    @Test
    void testFindAll() {
        List<Question> list = Arrays.asList(entity);
        when(questionRepository.findAll()).thenReturn(list);
        List<QuestionResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindQuestionsAtivas() {
        List<Question> list = Arrays.asList(entity);
        when(questionRepository.findQuestionsAtivas()).thenReturn(list);
        List<QuestionResponse> responses = service.findQuestionsAtivas();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemove() {
        service.remove(entity);
        verify(questionRepository).delete(entity);
    }
}
