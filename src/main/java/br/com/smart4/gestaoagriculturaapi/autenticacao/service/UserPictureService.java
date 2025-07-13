package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.UserPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPictureService {
	
	@Autowired
	private UserPictureRepository userPictureRepository;

	public UserPicture create(UserPicture userPicture) {
		return userPictureRepository.saveAndFlush(userPicture);
	}
	
	public UserPicture createOrAtualiza(UserPicture userPicture) {
		return userPictureRepository.saveAndFlush(userPicture);
	}
	
	public UserPicture atualiza(UserPicture userPicture) {
		return userPictureRepository.save(userPicture);
	}
	
	public void atualizaUsuarioFoto(UserPicture userPicture) {
		userPictureRepository.save(userPicture);
	}
	
	public List<UserPicture> findAll() {
		return userPictureRepository.findAll();
	}

	public Optional<UserPicture> findById(Long id) {
		return userPictureRepository.findById(id);
	}

	public Optional<UserPicture> findByUsuarioLogin(String login) {
		return userPictureRepository.findByUsuarioLogin(login);
	}

	public void remove(UserPicture userPicture) {
		userPictureRepository.delete(userPicture);
	}
	
}
