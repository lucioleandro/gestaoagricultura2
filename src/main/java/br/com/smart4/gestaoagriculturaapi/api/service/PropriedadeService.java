package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Propriedade;
import br.com.smart4.gestaoagriculturaapi.api.repository.PropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropriedadeService {
	
	@Autowired
	private PropriedadeRepository propriedadeRepository;
	
	public Propriedade create(Propriedade propriedade) {
		return propriedadeRepository.save(propriedade);
	}
	
	public Propriedade atualiza(Propriedade propriedade) {
		return propriedadeRepository.save(propriedade);
	}
	
	public Optional<Propriedade> findById(Long id) {
		return propriedadeRepository.findById(id);
	}
	
	public List<Propriedade> findAll() {
		return propriedadeRepository.findAll();
	}

	public List<Propriedade> findByAgricultor(Long id) {
		return propriedadeRepository.findByAgricultorId(id);
	}
	
	public void remove(Propriedade propriedade) {
		propriedadeRepository.delete(propriedade);
	}
	
}
