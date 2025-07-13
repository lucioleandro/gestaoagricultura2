package br.com.smart4.gestaoagriculturaapi.sistema.service;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Parameters;
import br.com.smart4.gestaoagriculturaapi.sistema.repository.ParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametersService {
	
	@Autowired
	private ParametersRepository parametroRepository;
	
	public Parameters create(Parameters parametro) {
		return parametroRepository.save(parametro);
	}

	public Parameters atualiza(Parameters parametro) {
		return parametroRepository.save(parametro);
	}

	public Optional<Parameters> findById(Long id) {
		return parametroRepository.findById(id);
	}

	public List<Parameters> findAll() {
		return parametroRepository.findAll();
	}

	public void remove(Parameters parametro) {
		parametroRepository.delete(parametro);
	}
	
}
