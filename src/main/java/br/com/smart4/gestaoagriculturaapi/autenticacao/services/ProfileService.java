package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

	private final ProfileRepository profileRepository;

	public ProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Transactional
	public Profile create(Profile perfil) {
		return profileRepository.saveAndFlush(perfil);
	}

	@Transactional
	public Profile atualiza(Profile perfil) {
		return profileRepository.save(perfil);
	}
	
	public List<Profile> findAll() {
		return profileRepository.findAll();
	}

	public Optional<Profile> findById(Long id) {
		return profileRepository.findById(id);
	}

	public List<Profile> findByIdUsuario(Long id) {
		return profileRepository.findByUserId(id);
	}

	@Transactional
	public void remove(Profile perfil) {
		profileRepository.delete(perfil);
	}

}
