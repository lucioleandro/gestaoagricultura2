package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Municipio;
import br.com.smart4.gestaoagriculturaapi.api.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {
	
	@Autowired
	private MunicipioRepository municipioRepository;
	
	public Municipio create(Municipio municipio) {
		return municipioRepository.save(municipio);
	}

	public Municipio atualiza(Municipio municipio) {
		return municipioRepository.save(municipio);
	}

	public Optional<Municipio> findById(Long id) {
		return municipioRepository.findById(id);
	}

	public List<Municipio> findAll() {
		return municipioRepository.findAll();
	}
	
	public void remove(Municipio municipio) {
		municipioRepository.delete(municipio);
	}
	
}
