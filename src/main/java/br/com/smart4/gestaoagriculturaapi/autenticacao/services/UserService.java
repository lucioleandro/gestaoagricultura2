package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.api.exceptions.BusinessException;
import br.com.smart4.gestaoagriculturaapi.api.exceptions.ResourceNotFoundException;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.UserFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.UserMapper;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = userRepository.findByName(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado");
		}
		return usuario;
	}

	@Transactional
	public UserResponse create(UserRequest user) {
		User saved = userRepository.saveAndFlush(UserFactory.fromRequest(user));
		return UserMapper.toResponse(saved);
	}

	@Transactional
	public UserResponse update(Long id, UserRequest user) {
		User entity = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

		entity.setNome(user.getNome());
		entity.setEmail(user.getEmail());
		entity.setLogin(user.getLogin());
		entity.setSenha(user.getSenha());

		return UserMapper.toResponse(userRepository.save(entity));
	}

	/**
	 * Change a user’s password, verifying the current password first.
	 * @throws ResourceNotFoundException if no user with that login exists (→ 404)
	 * @throws BusinessException if the current password does not match (→ 400)
	 */
	@Transactional
	public UserResponse changePassword(
			String login,
			String currentPassword,
			String newPassword
	) {
		User user = userRepository.findByLogin(login)
				.orElseThrow(() ->
						new ResourceNotFoundException("Usuário não encontrado com login: " + login)
				);

		if (!passwordEncoder.matches(currentPassword, user.getSenha())) {
			throw new BusinessException("A senha atual informada está incorreta");
		}

		user.setSenha(passwordEncoder.encode(newPassword));
		User saved = userRepository.save(user);
		return UserMapper.toResponse(saved);
	}


	/**
	 * Update an existing user's basic data (name, login, email),
	 * looked up by their current login.
	 * @throws ResourceNotFoundException if no user with `oldLogin` exists
	 */
	@Transactional
	public UserResponse updateBasicData(
			String oldLogin,
			String newName,
			String newLogin,
			String newEmail
	) {
		User user = userRepository.findByLogin(oldLogin)
				.orElseThrow(() ->
						new ResourceNotFoundException("User not found with login: " + oldLogin)
				);

		user.setNome(newName);
		user.setLogin(newLogin);
		user.setEmail(newEmail);

		User saved = userRepository.save(user);

		return UserMapper.toResponse(saved);
	}

	public List<UserResponse> findAll() {
		return UserMapper.toListResponse(userRepository.findAll());
	}

	public UserResponse findById(Long id) {
		User entity = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		return UserMapper.toResponse(entity);
	}

	public Optional<UserResponse> findByLogin(String login) {
		return userRepository.findByLogin(login)
				.map(UserMapper::toResponse);
	}

	@Transactional
	public void remove(Long id) {
		User entity = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		userRepository.delete(entity);
	}
}
