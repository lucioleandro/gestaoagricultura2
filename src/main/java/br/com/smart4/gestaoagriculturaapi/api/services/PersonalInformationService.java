package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PersonalInformationRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.PersonalInformationFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PersonalInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInformationService {

	private final PersonalInformationRepository personalInformationRepository;

	public PersonalInformationService(PersonalInformationRepository personalInformationRepository) {
		this.personalInformationRepository = personalInformationRepository;
	}

	public PersonalInformation create(PersonalInformationRequest personalInformation) {
		return personalInformationRepository.save(PersonalInformationFactory.fromRequest(personalInformation));
	}

	public PersonalInformation atualiza(PersonalInformationRequest personalInformation) {
		return personalInformationRepository.save(PersonalInformationFactory.fromRequest(personalInformation));
	}

	public Optional<PersonalInformation> findById(Long id) {
		return personalInformationRepository.findById(id);
	}

	public List<PersonalInformation> findAll() {
		return personalInformationRepository.findAll();
	}
	
	public void remove(PersonalInformation personalInformation) {
		personalInformationRepository.delete(personalInformation);
	}
	
}
