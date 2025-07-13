package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.repository.EconomicActivityFarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EconomicActivityFarmerService {
	
	@Autowired
	private EconomicActivityFarmerRepository economicActivityRepository;
	
	public EconomicActivityFarmer create(EconomicActivityFarmer municipio) {
		return economicActivityRepository.save(municipio);
	}

	public EconomicActivityFarmer atualiza(EconomicActivityFarmer municipio) {
		return economicActivityRepository.save(municipio);
	}

	public Optional<EconomicActivityFarmer> findById(Long id) {
		return economicActivityRepository.findById(id);
	}

	public List<EconomicActivityFarmer> findAll() {
		return economicActivityRepository.findAll();
	}

	public List<EconomicActivityFarmer> findByFarmer(Long id) {
		return economicActivityRepository.findByFarmerId(id);
	}

	public List<EconomicActivityFarmer> findByProperty(Long id) {
		return economicActivityRepository.findByPropertyId(id);
	}

	public void remove(EconomicActivityFarmer municipio) {
		economicActivityRepository.delete(municipio);
	}
	
}
