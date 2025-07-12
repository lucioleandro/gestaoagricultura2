package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Agricultor;
import br.com.smart4.gestaoagriculturaapi.api.repository.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgricultorService {

	@Autowired
	private AgricultorRepository agricultorRepository;

	public Agricultor create(Agricultor agricultor) {
		return agricultorRepository.save(agricultor);
	}

	public Agricultor atualiza(Agricultor agricultor) {
		return agricultorRepository.save(agricultor);
	}

	public Optional<Agricultor> findById(Long id) {
		return agricultorRepository.findById(id);
	}

	public List<Agricultor> findAll() {
		return agricultorRepository.findAll();
	}

	public Optional<Agricultor> findByCpf(String cpf) {
		return agricultorRepository.findByCpf(cpf);
	}

	public void remove(Agricultor agricultor) {
		agricultorRepository.delete(agricultor);
	}
	
}
