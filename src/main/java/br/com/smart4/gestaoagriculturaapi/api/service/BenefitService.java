package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Benefit;
import br.com.smart4.gestaoagriculturaapi.api.repository.BenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenefitService {

	@Autowired
	private BenefitRepository benefitRepository;

	public void create(Benefit benefit) {
		benefitRepository.save(benefit);
	}

	public void atualiza(Benefit benefit) {
		benefitRepository.save(benefit);
	}

	public Optional<Benefit> findById(Long id) {
		return benefitRepository.findById(id);
	}

	public List<Benefit> findAll() {
		return benefitRepository.findAll();
	}

	public void remove(Benefit benefit) {
		benefitRepository.delete(benefit);
	}
	
}
