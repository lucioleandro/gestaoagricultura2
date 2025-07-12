package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadeEconomica;
import br.com.smart4.gestaoagriculturaapi.api.repository.AtividadeEconomicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeEconomicaService {
	
	@Autowired
	private AtividadeEconomicaRepository atividadeEconomicaRepository;
	
	public AtividadeEconomica create(AtividadeEconomica atividadeEconomica) {
		return atividadeEconomicaRepository.save(atividadeEconomica);
	}

	public AtividadeEconomica atualiza(AtividadeEconomica atividadeEconomica) {
		return atividadeEconomicaRepository.save(atividadeEconomica);
	}

	public Optional<AtividadeEconomica> findById(Long id) {
		return atividadeEconomicaRepository.findById(id);
	}

	public List<AtividadeEconomica> findAll() {
		return atividadeEconomicaRepository.findAll();
	}

	public void remove(AtividadeEconomica atividadeEconomica) {
		atividadeEconomicaRepository.delete(atividadeEconomica);
	}
	
}
