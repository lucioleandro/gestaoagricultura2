package br.com.smart4.gestaoagriculturaapi.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.MaritalStatusEnum;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PersonalInformationRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PersonalInformationResponse;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PersonalInformationRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
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
class PersonalInformationServiceTest {

    @Mock
    private PersonalInformationRepository personalInformationRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @InjectMocks
    private PersonalInformationService service;

    private PersonalInformationRequest request;
    private PersonalInformation entity;
    private Farmer farmer;

    @BeforeEach
    void setUp() {
        farmer = new Farmer();
        farmer.setId(55L);

        request = new PersonalInformationRequest();
        request.setApelido("Nick");
        request.setMae("Maria");
        request.setPai("João");
        request.setMaritalStatus(MaritalStatusEnum.MARRIED);
        request.setNomeConjugue("");
        request.setFarmerId(farmer.getId());

        entity = new PersonalInformation();
        entity.setId(66L);
        entity.setApelido(request.getApelido());
        entity.setMae(request.getMae());
        entity.setPai(request.getPai());
        entity.setMaritalStatus(request.getMaritalStatus());
        entity.setNomeConjugue(request.getNomeConjugue());
        entity.setFarmer(farmer);
    }

    @Test
    void testCreate() {
        when(personalInformationRepository.save(any(PersonalInformation.class))).thenReturn(entity);

        PersonalInformationResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(request.getApelido(), response.getApelido());
        assertEquals(request.getMae(), response.getMae());
        assertEquals(request.getPai(), response.getPai());
        assertEquals(request.getMaritalStatus(), response.getMaritalStatus());
        assertEquals(request.getNomeConjugue(), response.getNomeConjugue());
        assertEquals(farmer.getId(), response.getFarmerId());

        verify(personalInformationRepository).save(any(PersonalInformation.class));
    }

    @Test
    void testUpdateSuccess() {
        Long id = entity.getId();
        when(personalInformationRepository.findById(id)).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(farmer.getId())).thenReturn(Optional.of(farmer));
        when(personalInformationRepository.save(any(PersonalInformation.class))).thenReturn(entity);

        PersonalInformationResponse response = service.update(id, request);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(request.getApelido(), response.getApelido());
        assertEquals(request.getMae(), response.getMae());
        assertEquals(request.getPai(), response.getPai());
        assertEquals(request.getMaritalStatus(), response.getMaritalStatus());
        assertEquals(request.getNomeConjugue(), response.getNomeConjugue());
        assertEquals(farmer.getId(), response.getFarmerId());

        verify(personalInformationRepository).findById(id);
        verify(farmerRepository).findById(request.getFarmerId());
        verify(personalInformationRepository).save(any(PersonalInformation.class));
    }

    @Test
    void testUpdateNotFound() {
        when(personalInformationRepository.findById(999L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(999L, request));
        assertTrue(ex.getMessage().contains("PersonalInformation not found"));
    }

    @Test
    void testUpdateFarmerNotFound() {
        when(personalInformationRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(farmerRepository.findById(request.getFarmerId())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.update(entity.getId(), request));
        assertTrue(ex.getMessage().contains("Farmer not found"));
    }

    @Test
    void testFindByIdSuccess() {
        when(personalInformationRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        PersonalInformationResponse response = service.findById(entity.getId());
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(personalInformationRepository.findById(123L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.findById(123L));
        assertTrue(ex.getMessage().contains("PersonalInformation not found"));
    }

    @Test
    void testFindAll() {
        List<PersonalInformation> list = Arrays.asList(entity);
        when(personalInformationRepository.findAll()).thenReturn(list);

        List<PersonalInformationResponse> responses = service.findAll();
        assertEquals(1, responses.size());
        assertEquals(entity.getId(), responses.get(0).getId());
    }

    @Test
    void testRemoveSuccess() {
        Long id = entity.getId();
        when(personalInformationRepository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(personalInformationRepository).delete(entity);

        service.remove(id);

        verify(personalInformationRepository).delete(entity);
    }

    @Test
    void testRemoveNotFound() {
        when(personalInformationRepository.findById(321L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> service.remove(321L));
        assertTrue(ex.getMessage().contains("PersonalInformation not found"));
    }
}

