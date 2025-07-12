package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Bairro;
import br.com.smart4.gestaoagriculturaapi.api.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BairroService {

	@Autowired
	private BairroRepository bairroRepository;

	public Bairro create(Bairro bairro) {
		return bairroRepository.save(bairro);
	}

	public Bairro atualiza(Bairro bairro) {
		return bairroRepository.save(bairro);
	}

	public Optional<Bairro> findById(Long id) {
		return bairroRepository.findById(id);
	}

	public List<Bairro> findAll() {
		return bairroRepository.findAll();
	}

	public List<Bairro> findByMunicipioNome(String municipio) {
		return bairroRepository.findByMunicipioNome(municipio);
	}
	
	public void remove(Bairro bairro) {
		bairroRepository.delete(bairro);
	}
	
}
