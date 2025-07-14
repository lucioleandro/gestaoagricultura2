package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.UserProfileFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.UserProfileMapper;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserProfileRepository;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserRepository;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProfileService {

	private final UserProfileRepository userProfileRepository;
	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;

	public UserProfileService(UserProfileRepository userProfileRepository,
							  UserRepository userRepository,
							  ProfileRepository profileRepository) {
		this.userProfileRepository = userProfileRepository;
		this.userRepository = userRepository;
		this.profileRepository = profileRepository;
	}

	@Transactional
	public UserProfileResponse create(UserProfileRequest request) {
		UserProfile saved = userProfileRepository.saveAndFlush(
				UserProfileFactory.fromRequest(request)
		);
		return UserProfileMapper.toResponse(saved);
	}

	@Transactional
	public UserProfileResponse update(Long id, UserProfileRequest request) {
		UserProfile existing = userProfileRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("UserProfile not found with id: " + id));

		existing.setAtivo(request.isAtivo());
		existing.setAdministrador(request.isAdministrador());

		existing.setUsuario(userRepository.findById(request.getUsuarioId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUsuarioId())));

		existing.setPerfil(profileRepository.findById(request.getPerfilId())
				.orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + request.getPerfilId())));

		return UserProfileMapper.toResponse(userProfileRepository.save(existing));
	}

	public List<UserProfileResponse> findAll() {
		return UserProfileMapper.toListResponse(userProfileRepository.findAll());
	}

	public UserProfileResponse findByUserId(Long id) {
		return userProfileRepository.findById(id)
				.map(UserProfileMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("UserProfile not found with id: " + id));
	}

	public List<UserProfileResponse> findListByUserId(Long id) {
		return UserProfileMapper.toListResponse(userProfileRepository.findByUsuarioId(id));
	}

	@Transactional
	public void remove(Long id) {
		UserProfile entity = userProfileRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("UserProfile not found with id: " + id));
		userProfileRepository.delete(entity);
	}
}
