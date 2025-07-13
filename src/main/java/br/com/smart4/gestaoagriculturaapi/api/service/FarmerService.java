package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {

	@Autowired
	private FarmerRepository farmerRepository;

	public Farmer create(Farmer farmer) {
		return farmerRepository.save(farmer);
	}

	public Farmer atualiza(Farmer farmer) {
		return farmerRepository.save(farmer);
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

	public void remove(Farmer farmer) {
		farmerRepository.delete(farmer);
	}
	
}
