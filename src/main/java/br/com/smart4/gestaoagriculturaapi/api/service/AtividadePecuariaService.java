package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadePecuaria;
import br.com.smart4.gestaoagriculturaapi.api.repository.AtividadePecuariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadePecuariaService {
	
	@Autowired
	private AtividadePecuariaRepository atividadePecuariaRepository;
	
	public AtividadePecuaria create(AtividadePecuaria atividadePecuaria) {
		return atividadePecuariaRepository.save(atividadePecuaria);
	}

	public AtividadePecuaria atualiza(AtividadePecuaria atividadePecuaria) {
		return atividadePecuariaRepository.save(atividadePecuaria);
	}

	public Optional<AtividadePecuaria> findById(Long id) {
		return atividadePecuariaRepository.findById(id);
	}

	public List<AtividadePecuaria> findAll() {
		return atividadePecuariaRepository.findAll();
	}

	public List<AtividadePecuaria> findByPropriedadeId(Long id) {
		return atividadePecuariaRepository.findByPropriedadeId(id);
	}

	public void remove(AtividadePecuaria atividadePecuaria) {
		atividadePecuariaRepository.delete(atividadePecuaria);
	}
	
}
