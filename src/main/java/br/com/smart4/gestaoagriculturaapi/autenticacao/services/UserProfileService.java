package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.UserProfileFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

	private final UserProfileRepository usuarioFotoRepository;

	public UserProfileService(UserProfileRepository usuarioFotoRepository) {
		this.usuarioFotoRepository = usuarioFotoRepository;
	}

	@Transactional
	public UserProfile create(UserProfileRequest userPicure) {
		return usuarioFotoRepository.saveAndFlush(UserProfileFactory.fromRequest(userPicure));
	}

	@Transactional
	public UserProfile atualiza(UserProfileRequest userPicure) {
		return usuarioFotoRepository.save(UserProfileFactory.fromRequest(userPicure));
	}
	
	public List<UserProfile> findAll() {
		return usuarioFotoRepository.findAll();
	}

	public Optional<UserProfile> findById(Long id) {
		return usuarioFotoRepository.findById(id);
	}

	public List<UserProfile> findByUsuarioId(Long id) {
		return usuarioFotoRepository.findByUsuarioId(id);
	}

	@Transactional
	public void remove(UserProfile usuarioFoto) {
		usuarioFotoRepository.delete(usuarioFoto);
	}

}
