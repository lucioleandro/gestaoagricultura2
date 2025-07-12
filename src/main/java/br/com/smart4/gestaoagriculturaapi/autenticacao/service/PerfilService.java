package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Perfil;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	public Perfil create(Perfil perfil) {
		return perfilRepository.saveAndFlush(perfil);
	}
	
	public Perfil atualiza(Perfil perfil) {
		return perfilRepository.save(perfil);
	}
	
	public void atualizaPerfil(Perfil perfil) {
		perfilRepository.save(perfil);
	}

	public List<Perfil> findAll() {
		return perfilRepository.findAll();
	}

	public Optional<Perfil> findById(Long id) {
		return perfilRepository.findById(id);
	}

	public List<Perfil> findByIdUsuario(Long id) {
		return perfilRepository.findByIdUsuario(id);
	}

	public void remove(Perfil perfil) {
		perfilRepository.delete(perfil);
	}

}
