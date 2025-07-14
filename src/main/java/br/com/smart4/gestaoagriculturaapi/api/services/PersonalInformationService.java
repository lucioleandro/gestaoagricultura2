package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PersonalInformationRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PersonalInformationResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.PersonalInformationFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.PersonalInformationMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PersonalInformationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonalInformationService {

	private final PersonalInformationRepository personalInformationRepository;
	private final FarmerRepository farmerRepository;

	public PersonalInformationService(PersonalInformationRepository personalInformationRepository, FarmerRepository farmerRepository) {
		this.personalInformationRepository = personalInformationRepository;
        this.farmerRepository = farmerRepository;
    }

	@Transactional
	public PersonalInformationResponse create(PersonalInformationRequest request) {
		PersonalInformation entity = personalInformationRepository.save(
				PersonalInformationFactory.fromRequest(request)
		);
		return PersonalInformationMapper.toResponse(entity);
	}

	@Transactional
	public PersonalInformationResponse update(Long id, PersonalInformationRequest request) {
		PersonalInformation existing = personalInformationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("PersonalInformation not found with id: " + id));

		Farmer farmer = farmerRepository.findById(request.getFarmerId())
				.orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + request.getFarmerId()));

		existing.setApelido(request.getApelido());
		existing.setMae(request.getMae());
		existing.setPai(request.getPai());
		existing.setMaritalStatus(request.getMaritalStatus());
		existing.setNomeConjugue(request.getNomeConjugue());
		existing.setFarmer(farmer);

		PersonalInformation updated = personalInformationRepository.save(existing);
		return PersonalInformationMapper.toResponse(updated);
	}

	public PersonalInformationResponse findById(Long id) {
		return personalInformationRepository.findById(id)
				.map(PersonalInformationMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("PersonalInformation not found with id: " + id));
	}

	public List<PersonalInformationResponse> findAll() {
		return PersonalInformationMapper.toListResponse(
				personalInformationRepository.findAll()
		);
	}

	@Transactional
	public void remove(Long id) {
		PersonalInformation existing = personalInformationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("PersonalInformation not found with id: " + id));
		personalInformationRepository.delete(existing);
	}
}
