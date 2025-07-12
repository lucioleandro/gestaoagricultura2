package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.DadosSociais;
import br.com.smart4.gestaoagriculturaapi.api.repository.DadosSociaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DadosSociaisService {

	@Autowired
	private DadosSociaisRepository dadosSociaisRepository;

	public DadosSociais create(DadosSociais dadosSociais) {
		return dadosSociaisRepository.save(dadosSociais);
	}

	public DadosSociais atualiza(DadosSociais dadosSociais) {
		return dadosSociaisRepository.save(dadosSociais);
	}

	public Optional<DadosSociais> findById(Long id) {
		return dadosSociaisRepository.findById(id);
	}

	public List<DadosSociais> findAll() {
		return dadosSociaisRepository.findAll();
	}
	
	public void remove(DadosSociais dadosSociais) {
		dadosSociaisRepository.delete(dadosSociais);
	}
	
}
