package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadeAgricola;
import br.com.smart4.gestaoagriculturaapi.api.repository.AtividadeAgricolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeAgricolaService {
	
	@Autowired
	private AtividadeAgricolaRepository atividadeAgriculaRepository;

	public AtividadeAgricola create(AtividadeAgricola atividadeAgricula) {
		return atividadeAgriculaRepository.save(atividadeAgricula);
	}

	public void atualiza(AtividadeAgricola atividadeAgricula) {
		atividadeAgriculaRepository.save(atividadeAgricula);
	}

	public Optional<AtividadeAgricola> findById(Long id) {
		return atividadeAgriculaRepository.findById(id);
	}

	public List<AtividadeAgricola> findAll() {
		return atividadeAgriculaRepository.findAll();
	}

	public List<AtividadeAgricola> findByPropriedade(Long id) {
		return atividadeAgriculaRepository.findByPropriedadeId(id);
	}
	
	public void remove(AtividadeAgricola atividadeAgricula) {
		atividadeAgriculaRepository.delete(atividadeAgricula);
	}
	
}
