package br.com.smart4.gestaoagriculturaapi.autenticacao.service;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Permissao;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao create(Permissao permissao) {
		return permissaoRepository.saveAndFlush(permissao);
	}
	
	public Permissao atualiza(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	public void atualizaPermissao(Permissao permissao) {
		permissaoRepository.save(permissao);
	}
	
	public List<Permissao> findAll() {
		return permissaoRepository.findAll();
	}

	public Optional<Permissao> findById(Long id) {
		return permissaoRepository.findById(id);
	}

	public List<Permissao> findByPerfilId(Long perfilId) {
		return permissaoRepository.findByPerfilId(perfilId);
	}

	public void remove(Permissao permissao) {
		permissaoRepository.delete(permissao);
	}

}
