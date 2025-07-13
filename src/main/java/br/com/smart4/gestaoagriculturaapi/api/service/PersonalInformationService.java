package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.PersonalInformation;
import br.com.smart4.gestaoagriculturaapi.api.repository.PersonalInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInformationService {

	@Autowired
	private PersonalInformationRepository personalInformationRepository;

	public PersonalInformation create(PersonalInformation personalInformation) {
		return personalInformationRepository.save(personalInformation);
	}

	public PersonalInformation atualiza(PersonalInformation personalInformation) {
		return personalInformationRepository.save(personalInformation);
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
