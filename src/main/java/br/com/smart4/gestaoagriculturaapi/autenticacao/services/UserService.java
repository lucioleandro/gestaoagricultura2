package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.UserFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.UserMapper;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
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
	public UserResponse update(UserRequest user) {
		User updated = userRepository.save(UserFactory.fromRequest(user));
		return UserMapper.toResponse(updated);
	}

	@Transactional
	public UserResponse update(User user) {
		User updated = userRepository.save(user);
		return UserMapper.toResponse(updated);
	}

	public List<UserResponse> findAll() {
		return UserMapper.toListResponse(userRepository.findAll());
	}

	public Optional<UserResponse> findById(Long id) {
		return userRepository.findById(id)
				.map(UserMapper::toResponse);
	}

	public Optional<UserResponse> findByLogin(String login) {
		return userRepository.findByLogin(login)
				.map(UserMapper::toResponse);
	}

	@Transactional
	public void remove(User usuario) {
		userRepository.delete(usuario);
	}
}
