package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.USerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private USerRepository USerRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = USerRepository.findByName(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado");
		}

		return usuario;
	}
	
	public User create(User usuario) {
		return USerRepository.saveAndFlush(usuario);
	}
	
	public User atualiza(User usuario) {
		return USerRepository.save(usuario);
	}
	
	public List<User> findAll() {
		return USerRepository.findAll();
	}

	public Optional<User> findById(Long id) {
		return USerRepository.findById(id);
	}

	public Optional<User> findByLogin(String login) {
		return USerRepository.findByLogin(login);
	}

	public void remove(User usuario) {
		USerRepository.delete(usuario);
	}

}
