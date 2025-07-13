package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepository usuarioFotoRepository;

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
