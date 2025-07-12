package br.com.smart4.gestaoagriculturaapi.sistema.service;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Parametros;
import br.com.smart4.gestaoagriculturaapi.sistema.repository.ParametrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametrosService {
	
	@Autowired
	private ParametrosRepository parametroRepository;
	
	public Parametros create(Parametros parametro) {
		return parametroRepository.save(parametro);
	}

	public Parametros atualiza(Parametros parametro) {
		return parametroRepository.save(parametro);
	}

	public Optional<Parametros> findById(Long id) {
		return parametroRepository.findById(id);
	}

	public List<Parametros> findAll() {
		return parametroRepository.findAll();
	}

	public void remove(Parametros parametro) {
		parametroRepository.delete(parametro);
	}
	
}
