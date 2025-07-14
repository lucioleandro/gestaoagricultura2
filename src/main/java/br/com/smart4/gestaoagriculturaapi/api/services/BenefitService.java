package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Benefit;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.BenefitRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.BenefitResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.BenefitFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.BenefitMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.BenefitRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BenefitService {

	private final BenefitRepository benefitRepository;
	private final FarmerRepository farmerRepository;

	public BenefitService(BenefitRepository benefitRepository, FarmerRepository farmerRepository) {
		this.benefitRepository = benefitRepository;
		this.farmerRepository = farmerRepository;
	}

	@Transactional
	public BenefitResponse create(BenefitRequest request) {
		farmerRepository.findById(request.getBeneficiadoId())
				.orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + request.getBeneficiadoId()));

		Benefit saved = benefitRepository.save(BenefitFactory.fromRequest(request));
		return BenefitMapper.toResponse(saved);
	}

	@Transactional
	public BenefitResponse update(Long id, BenefitRequest request) {
		Benefit existing = benefitRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Benefit not found with id: " + id));

		Farmer farmer = farmerRepository.findById(request.getBeneficiadoId())
				.orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + request.getBeneficiadoId()));

		existing.setDescricao(request.getDescricao());
		existing.setDataConcedimento(request.getDataConcedimento());
		existing.setBeneficiado(farmer);

		Benefit updated = benefitRepository.save(existing);
		return BenefitMapper.toResponse(updated);
	}

	public BenefitResponse findById(Long id) {
		return benefitRepository.findById(id)
				.map(BenefitMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Benefit not found with id: " + id));
	}

	public List<BenefitResponse> findAll() {
		return BenefitMapper.toListResponse(benefitRepository.findAll());
	}

	@Transactional
	public void remove(Long id) {
		Benefit existing = benefitRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Benefit not found with id: " + id));

		benefitRepository.delete(existing);
	}
}
