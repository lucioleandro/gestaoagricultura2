package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadeEconomicaAgricultor;
import br.com.smart4.gestaoagriculturaapi.api.repository.AtividadeEconomicaAgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeEconomicaAgricultorService {
	
	@Autowired
	private AtividadeEconomicaAgricultorRepository atividadeEconomicaRepository;
	
	public AtividadeEconomicaAgricultor create(AtividadeEconomicaAgricultor municipio) {
		return atividadeEconomicaRepository.save(municipio);
	}

	public AtividadeEconomicaAgricultor atualiza(AtividadeEconomicaAgricultor municipio) {
		return atividadeEconomicaRepository.save(municipio);
	}

	public Optional<AtividadeEconomicaAgricultor> findById(Long id) {
		return atividadeEconomicaRepository.findById(id);
	}

	public List<AtividadeEconomicaAgricultor> findAll() {
		return atividadeEconomicaRepository.findAll();
	}

	public List<AtividadeEconomicaAgricultor> findByAgricultor(Long id) {
		return atividadeEconomicaRepository.findByAgricultorId(id);
	}

	public List<AtividadeEconomicaAgricultor> findByPropriedade(Long id) {
		return atividadeEconomicaRepository.findByPropriedadeId(id);
	}

	public void remove(AtividadeEconomicaAgricultor municipio) {
		atividadeEconomicaRepository.delete(municipio);
	}
	
}
