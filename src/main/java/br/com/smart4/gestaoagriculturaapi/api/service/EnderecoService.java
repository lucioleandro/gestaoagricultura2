package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Endereco;
import br.com.smart4.gestaoagriculturaapi.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco create(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Endereco atualiza(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Optional<Endereco> findById(Long id) {
		return enderecoRepository.findById(id);
	}

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	public void remove(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}
	
}
