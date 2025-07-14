package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PersonalInformationRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PersonalInformationResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.PersonalInformationFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.PersonalInformationMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PersonalInformationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInformationService {

	private final PersonalInformationRepository personalInformationRepository;

	public PersonalInformationService(PersonalInformationRepository personalInformationRepository) {
		this.personalInformationRepository = personalInformationRepository;
	}

	@Transactional
	public PersonalInformationResponse create(PersonalInformationRequest personalInformation) {
		PersonalInformation entity = personalInformationRepository.save(
				PersonalInformationFactory.fromRequest(personalInformation)
		);
		return PersonalInformationMapper.toResponse(entity);
	}

	@Transactional
	public PersonalInformationResponse update(PersonalInformationRequest personalInformation) {
		PersonalInformation entity = personalInformationRepository.save(
				PersonalInformationFactory.fromRequest(personalInformation)
		);
		return PersonalInformationMapper.toResponse(entity);
	}

	public Optional<PersonalInformationResponse> findById(Long id) {
		return personalInformationRepository.findById(id)
				.map(PersonalInformationMapper::toResponse);
	}

	public List<PersonalInformationResponse> findAll() {
		return PersonalInformationMapper.toListResponse(
				personalInformationRepository.findAll()
		);
	}

	@Transactional
	public void remove(PersonalInformation personalInformation) {
		personalInformationRepository.delete(personalInformation);
	}
}
