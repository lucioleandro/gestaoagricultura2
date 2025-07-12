package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UsuarioFoto;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.UsuarioFotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioFotoService {
	
	@Autowired
	private UsuarioFotoRepository usuarioFotoRepository;

	public UsuarioFoto create(UsuarioFoto usuarioFoto) {
		return usuarioFotoRepository.saveAndFlush(usuarioFoto);
	}
	
	public UsuarioFoto createOrAtualiza(UsuarioFoto usuarioFoto) {
		return usuarioFotoRepository.saveAndFlush(usuarioFoto);
	}
	
	public UsuarioFoto atualiza(UsuarioFoto usuarioFoto) {
		return usuarioFotoRepository.save(usuarioFoto);
	}
	
	public void atualizaUsuarioFoto(UsuarioFoto usuarioFoto) {
		usuarioFotoRepository.save(usuarioFoto);
	}
	
	public List<UsuarioFoto> findAll() {
		return usuarioFotoRepository.findAll();
	}

	public Optional<UsuarioFoto> findById(Long id) {
		return usuarioFotoRepository.findById(id);
	}

	public Optional<UsuarioFoto> findByUsuarioLogin(String login) {
		return usuarioFotoRepository.findByUsuarioLogin(login);
	}

	public void remove(UsuarioFoto usuarioFoto) {
		usuarioFotoRepository.delete(usuarioFoto);
	}
	
}
