package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserPictureRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserPictureResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.UserPictureFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.UserPictureMapper;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserPictureRepository;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserPictureService {

	private final UserPictureRepository userPictureRepository;
	private final UserRepository userRepository;

	public UserPictureService(UserPictureRepository userPictureRepository,
							  UserRepository userRepository) {
		this.userPictureRepository = userPictureRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public UserPictureResponse create(UserPictureRequest request) {
		userRepository.findById(request.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUserId()));

		UserPicture saved = userPictureRepository.saveAndFlush(UserPictureFactory.fromRequest(request));
		return UserPictureMapper.toResponse(saved);
	}

	@Transactional
	public UserPictureResponse update(UserPictureRequest request) {
		UserPicture entity = userPictureRepository.findByUsuarioId(request.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("UserPicture not found for user id: " + request.getUserId()));

		entity.setFotoPerfil(request.getFotoPerfil());
		return UserPictureMapper.toResponse(userPictureRepository.save(entity));
	}

	public List<UserPictureResponse> findAll() {
		return UserPictureMapper.toListResponse(userPictureRepository.findAll());
	}

	public UserPictureResponse findById(Long id) {
		return userPictureRepository.findById(id)
				.map(UserPictureMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("UserPicture not found with id: " + id));
	}

	public UserPictureResponse findByUsuarioLogin(String login) {
		return userPictureRepository.findByUsuarioLogin(login)
				.map(UserPictureMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("UserPicture not found for login: " + login));
	}

	@Transactional
	public void remove(Long id) {
		UserPicture entity = userPictureRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("UserPicture not found with id: " + id));
		userPictureRepository.delete(entity);
	}
}
