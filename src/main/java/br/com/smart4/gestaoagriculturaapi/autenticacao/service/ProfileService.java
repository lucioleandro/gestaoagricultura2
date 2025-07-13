package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
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
