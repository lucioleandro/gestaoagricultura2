package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

	private final UserProfileRepository usuarioFotoRepository;

	public UserProfileService(UserProfileRepository usuarioFotoRepository) {
		this.usuarioFotoRepository = usuarioFotoRepository;
	}

	public UserProfile create(UserProfile usuarioFoto) {
		return usuarioFotoRepository.saveAndFlush(usuarioFoto);
	}
	
	public UserProfile atualiza(UserProfile usuarioFoto) {
		return usuarioFotoRepository.save(usuarioFoto);
	}
	
	public void atualizaUsuarioPerfil(UserProfile usuarioFoto) {
		usuarioFotoRepository.save(usuarioFoto);
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

	public void remove(UserProfile usuarioFoto) {
		usuarioFotoRepository.delete(usuarioFoto);
	}

}
