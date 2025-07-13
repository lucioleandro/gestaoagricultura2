package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

	private final ProfileRepository profileRepository;

	public ProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	public Profile create(Profile perfil) {
		return profileRepository.saveAndFlush(perfil);
	}
	
	public Profile atualiza(Profile perfil) {
		return profileRepository.save(perfil);
	}
	
	public void atualizaPerfil(Profile perfil) {
		profileRepository.save(perfil);
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

	public void remove(Profile perfil) {
		profileRepository.delete(perfil);
	}

}
