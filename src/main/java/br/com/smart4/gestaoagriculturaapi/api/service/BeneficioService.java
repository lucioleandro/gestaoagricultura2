package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Beneficio;
import br.com.smart4.gestaoagriculturaapi.api.repository.BeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficioService {

	@Autowired
	private BeneficioRepository beneficioRepository;

	public void create(Beneficio beneficio) {
		beneficioRepository.save(beneficio);
	}

	public void atualiza(Beneficio beneficio) {
		beneficioRepository.save(beneficio);
	}

	public Optional<Beneficio> findById(Long id) {
		return beneficioRepository.findById(id);
	}

	public List<Beneficio> findAll() {
		return beneficioRepository.findAll();
	}

	public void remove(Beneficio beneficio) {
		beneficioRepository.delete(beneficio);
	}
	
}
