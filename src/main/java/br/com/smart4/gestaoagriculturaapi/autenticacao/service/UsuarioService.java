package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Usuario;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByName(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado");
		}

		return usuario;
	}
	
	public Usuario create(Usuario usuario) {
		return usuarioRepository.saveAndFlush(usuario);
	}
	
	public Usuario atualiza(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}

	public Optional<Usuario> findByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	public void remove(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

}
