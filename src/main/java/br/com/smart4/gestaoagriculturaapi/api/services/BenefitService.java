package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Benefit;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.BenefitRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.BenefitResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.BenefitFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.BenefitMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.BenefitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BenefitService {

	private final BenefitRepository benefitRepository;

	public BenefitService(BenefitRepository benefitRepository) {
		this.benefitRepository = benefitRepository;
	}

	@Transactional
	public BenefitResponse create(BenefitRequest benefit) {
		Benefit saved = benefitRepository.save(BenefitFactory.fromRequest(benefit));
		return BenefitMapper.toResponse(saved);
	}

	@Transactional
	public BenefitResponse update(BenefitRequest benefit) {
		Benefit updated = benefitRepository.save(BenefitFactory.fromRequest(benefit));
		return BenefitMapper.toResponse(updated);
	}

	public Optional<BenefitResponse> findById(Long id) {
		return benefitRepository.findById(id)
				.map(BenefitMapper::toResponse);
	}

	public List<BenefitResponse> findAll() {
		return BenefitMapper.toListResponse(benefitRepository.findAll());
	}

	@Transactional
	public void remove(Benefit benefit) {
		benefitRepository.delete(benefit);
	}
}
