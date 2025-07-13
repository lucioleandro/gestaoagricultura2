package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Benefit;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.BenefitRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.BenefitFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.BenefitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenefitService {

	private final BenefitRepository benefitRepository;

	public BenefitService(BenefitRepository benefitRepository) {
		this.benefitRepository = benefitRepository;
	}

	public void create(BenefitRequest benefit) {
		benefitRepository.save(BenefitFactory.fromRequest(benefit));
	}

	public void atualiza(BenefitRequest benefit) {
		benefitRepository.save(BenefitFactory.fromRequest(benefit));
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
