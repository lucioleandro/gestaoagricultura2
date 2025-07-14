package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.FarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.FarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.FarmerFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.FarmerMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {

	private final FarmerRepository farmerRepository;

	public FarmerService(FarmerRepository farmerRepository) {
		this.farmerRepository = farmerRepository;
	}

	@Transactional
	public FarmerResponse create(FarmerRequest farmer) {
		Farmer entity = farmerRepository.save(FarmerFactory.fromRequest(farmer));
		return FarmerMapper.toResponse(entity);
	}

	@Transactional
	public FarmerResponse update(FarmerRequest farmer) {
		Farmer entity = farmerRepository.save(FarmerFactory.fromRequest(farmer));
		return FarmerMapper.toResponse(entity);
	}

	public Optional<FarmerResponse> findById(Long id) {
		return farmerRepository.findById(id)
				.map(FarmerMapper::toResponse);
	}

	public List<FarmerResponse> findAll() {
		return FarmerMapper.toListResponse(farmerRepository.findAll());
	}

	public Optional<FarmerResponse> findByCpf(String cpf) {
		return farmerRepository.findByCpf(cpf)
				.map(FarmerMapper::toResponse);
	}

	@Transactional
	public void remove(Farmer farmer) {
		farmerRepository.delete(farmer);
	}
}
