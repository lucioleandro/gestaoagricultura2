package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.FarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.FarmerFactory;
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
	public Farmer create(FarmerRequest farmer) {
		return farmerRepository.save(FarmerFactory.fromRequest(farmer));
	}

	@Transactional
	public Farmer update(FarmerRequest farmer) {
		return farmerRepository.save(FarmerFactory.fromRequest(farmer));
	}

	public Optional<Farmer> findById(Long id) {
		return farmerRepository.findById(id);
	}

	public List<Farmer> findAll() {
		return farmerRepository.findAll();
	}

	public Optional<Farmer> findByCpf(String cpf) {
		return farmerRepository.findByCpf(cpf);
	}

	@Transactional
	public void remove(Farmer farmer) {
		farmerRepository.delete(farmer);
	}
	
}
