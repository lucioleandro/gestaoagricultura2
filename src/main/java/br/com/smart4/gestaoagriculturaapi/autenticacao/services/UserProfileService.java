package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.UserProfileFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.UserProfileMapper;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

	private final UserProfileRepository userProfileRepository;

	public UserProfileService(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Transactional
	public UserProfileResponse create(UserProfileRequest userProfile) {
		UserProfile entity = userProfileRepository.saveAndFlush(UserProfileFactory.fromRequest(userProfile));
		return UserProfileMapper.toResponse(entity);
	}

	@Transactional
	public UserProfileResponse update(UserProfileRequest userProfile) {
		UserProfile entity = userProfileRepository.save(UserProfileFactory.fromRequest(userProfile));
		return UserProfileMapper.toResponse(entity);
	}

	public List<UserProfileResponse> findAll() {
		return UserProfileMapper.toListResponse(userProfileRepository.findAll());
	}

	public Optional<UserProfileResponse> findById(Long id) {
		return userProfileRepository.findById(id)
				.map(UserProfileMapper::toResponse);
	}

	public List<UserProfileResponse> findByUsuarioId(Long id) {
		return UserProfileMapper.toListResponse(userProfileRepository.findByUsuarioId(id));
	}

	@Transactional
	public void remove(UserProfile userProfile) {
		userProfileRepository.delete(userProfile);
	}
}
