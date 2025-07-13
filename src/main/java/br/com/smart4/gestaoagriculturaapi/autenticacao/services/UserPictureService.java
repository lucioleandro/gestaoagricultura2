package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserPictureRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.UserPictureFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserPictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserPictureService {
	
	private final UserPictureRepository userPictureRepository;

	public UserPictureService(UserPictureRepository userPictureRepository) {
		this.userPictureRepository = userPictureRepository;
	}

	@Transactional
	public UserPicture create(UserPictureRequest userPicture) {
		return userPictureRepository.saveAndFlush(UserPictureFactory.fromRequest(userPicture));
	}

	@Transactional
	public UserPicture createOrAtualiza(UserPicture userPicture) {
		return userPictureRepository.saveAndFlush(userPicture);
	}

	@Transactional
	public UserPicture atualiza(UserPictureRequest userPicture) {
		return userPictureRepository.save(UserPictureFactory.fromRequest(userPicture));
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

	@Transactional
	public void remove(UserPicture userPicture) {
		userPictureRepository.delete(userPicture);
	}
	
}
