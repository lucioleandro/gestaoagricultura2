package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UsuarioPerfil;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.UsuarioPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioPerfilService {

	@Autowired
	private UsuarioPerfilRepository usuarioFotoRepository;

	public UsuarioPerfil create(UsuarioPerfil usuarioFoto) {
		return usuarioFotoRepository.saveAndFlush(usuarioFoto);
	}
	
	public UsuarioPerfil atualiza(UsuarioPerfil usuarioFoto) {
		return usuarioFotoRepository.save(usuarioFoto);
	}
	
	public void atualizaUsuarioPerfil(UsuarioPerfil usuarioFoto) {
		usuarioFotoRepository.save(usuarioFoto);
	}
	
	public List<UsuarioPerfil> findAll() {
		return usuarioFotoRepository.findAll();
	}

	public Optional<UsuarioPerfil> findById(Long id) {
		return usuarioFotoRepository.findById(id);
	}

	public List<UsuarioPerfil> findByUsuarioId(Long id) {
		return usuarioFotoRepository.findByUsuarioId(id);
	}

	public void remove(UsuarioPerfil usuarioFoto) {
		usuarioFotoRepository.delete(usuarioFoto);
	}

}
