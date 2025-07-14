package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserPictureRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserPictureResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.UserPictureFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.UserPictureMapper;
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
	public UserPictureResponse create(UserPictureRequest userPicture) {
		UserPicture entity = userPictureRepository.saveAndFlush(UserPictureFactory.fromRequest(userPicture));
		return UserPictureMapper.toResponse(entity);
	}

	@Transactional
	public UserPictureResponse createOrUpdate(UserPicture userPicture) {
		UserPicture entity = userPictureRepository.saveAndFlush(userPicture);
		return UserPictureMapper.toResponse(entity);
	}

	@Transactional
	public UserPictureResponse update(UserPictureRequest userPicture) {
		UserPicture entity = userPictureRepository.save(UserPictureFactory.fromRequest(userPicture));
		return UserPictureMapper.toResponse(entity);
	}

	public List<UserPictureResponse> findAll() {
		return UserPictureMapper.toListResponse(userPictureRepository.findAll());
	}

	public Optional<UserPictureResponse> findById(Long id) {
		return userPictureRepository.findById(id)
				.map(UserPictureMapper::toResponse);
	}

	public Optional<UserPictureResponse> findByUsuarioLogin(String login) {
		return userPictureRepository.findByUsuarioLogin(login)
				.map(UserPictureMapper::toResponse);
	}

	@Transactional
	public void remove(UserPicture userPicture) {
		userPictureRepository.delete(userPicture);
	}
}
