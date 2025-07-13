package br.com.smart4.gestaoagriculturaapi.sistema.services;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Parameters;
import br.com.smart4.gestaoagriculturaapi.sistema.repositories.ParametersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParametersService {
	
	private final ParametersRepository parametroRepository;

	public ParametersService(ParametersRepository parametroRepository) {
		this.parametroRepository = parametroRepository;
	}

	@Transactional
	public Parameters create(Parameters parametro) {
		return parametroRepository.save(parametro);
	}

	@Transactional
	public Parameters update(Parameters parametro) {
		return parametroRepository.save(parametro);
	}

	public Optional<Parameters> findById(Long id) {
		return parametroRepository.findById(id);
	}

	public List<Parameters> findAll() {
		return parametroRepository.findAll();
	}

	@Transactional
	public void remove(Parameters parametro) {
		parametroRepository.delete(parametro);
	}
	
}
