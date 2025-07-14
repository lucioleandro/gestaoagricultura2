package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Question;
import br.com.smart4.gestaoagriculturaapi.api.domains.QuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AnsweredQuestionResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.QuestionResponseRepository;
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
class QuestionResponseServiceTest {

    @Mock
    private QuestionResponseRepository questionResponseRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @InjectMocks
    private QuestionResponseService service;

    private ResponseQuestionRequest request;
    private QuestionResponse entity;
    private Question question;
    private Farmer farmer;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setId(1L);
        farmer = new Farmer();
        farmer.setId(2L);

        request = new ResponseQuestionRequest();
        request.setDescricao("Resposta A");
        request.setQuestionId(question.getId());
        request.setFarmerId(farmer.getId());

        entity = new QuestionResponse();
        entity.setId(100L);
        entity.setDescricao(request.getDescricao());
        entity.setQuestion(question);
        entity.setFarmer(farmer);
    }

    @Test
    void testCreate() {
        when(questionResponseRepository.save(any(QuestionResponse.class))).thenReturn(entity);

        AnsweredQuestionResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getDescricao(), response.getDescricao());
        verify(questionResponseRepository).save(any(QuestionResponse.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(questionResponseRepository.findById(id)).thenReturn(Optional.of(entity));
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        when(farmerRepository.findById(farmer.getId())).thenReturn(Optional.of(farmer));
        when(questionResponseRepository.save(any(QuestionResponse.class))).thenReturn(entity);

        AnsweredQuestionResponse response = service.update(id, request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getDescricao(), response.getDescricao());
        verify(questionResponseRepository).findById(id);
        verify(questionRepository).findById(request.getQuestionId());
        verify(farmerRepository).findById(request.getFarmerId());
        verify(questionResponseRepository).save(any(QuestionResponse.class));
    }

    @Test
    void testUpdateNotFound() {
        when(questionResponseRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("QuestionResponse not found"));
    }

    @Test
    void testUpdateQuestionNotFound() {
        when(questionResponseRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(questionRepository.findById(request.getQuestionId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Question not found"));
    }

    @Test
    void testUpdateFarmerNotFound() {
        when(questionResponseRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(questionRepository.findById(request.getQuestionId())).thenReturn(Optional.of(question));
        when(farmerRepository.findById(request.getFarmerId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }

    @Test
    void testFindByIdExists() {
        when(questionResponseRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        Optional<AnsweredQuestionResponse> opt = service.findById(entity.getId());
        assertTrue(opt.isPresent());
        assertEquals(entity.getId(), opt.get().getId());
    }

    @Test
    void testFindByIdNotExists() {
        when(questionResponseRepository.findById(123L)).thenReturn(Optional.empty());
        Optional<AnsweredQuestionResponse> opt = service.findById(123L);
        assertTrue(opt.isEmpty());
    }

    @Test
    void testFindAll() {
        List<QuestionResponse> list = Arrays.asList(entity);
        when(questionResponseRepository.findAll()).thenReturn(list);
        List<AnsweredQuestionResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByQuestion() {
        List<QuestionResponse> list = Arrays.asList(entity);
        when(questionResponseRepository.findByQuestionId(question.getId())).thenReturn(list);
        List<AnsweredQuestionResponse> responses = service.findByQuestion(question.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testFindByFarmer() {
        List<QuestionResponse> list = Arrays.asList(entity);
        when(questionResponseRepository.findByFarmerId(farmer.getId())).thenReturn(list);
        List<AnsweredQuestionResponse> responses = service.findByFarmerId(farmer.getId());
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveRespostasMultiplaEscolhaByFarmer() {
        doNothing().when(questionResponseRepository).removeRespostasMultiplaEscolhaByFarmer(farmer.getId());
        service.removeRespostasMultiplaEscolhaByFarmer(farmer.getId());
        verify(questionResponseRepository).removeRespostasMultiplaEscolhaByFarmer(farmer.getId());
    }

    @Test
    void testRemove() {
        service.remove(entity);
        verify(questionResponseRepository).delete(entity);
    }
}

