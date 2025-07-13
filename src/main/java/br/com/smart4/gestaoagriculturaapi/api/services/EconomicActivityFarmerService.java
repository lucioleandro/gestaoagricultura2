package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityFarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.EconomicActivityFarmerFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityFarmerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EconomicActivityFarmerService {
	
	private final EconomicActivityFarmerRepository economicActivityRepository;

	public EconomicActivityFarmerService(EconomicActivityFarmerRepository economicActivityRepository) {
		this.economicActivityRepository = economicActivityRepository;
	}

	@Transactional
	public EconomicActivityFarmer create(EconomicActivityFarmerRequest municipio) {
		return economicActivityRepository.save(EconomicActivityFarmerFactory.fromRequest(municipio));
	}

	@Transactional
	public EconomicActivityFarmer atualiza(EconomicActivityFarmerRequest municipio) {
		return economicActivityRepository.save(EconomicActivityFarmerFactory.fromRequest(municipio));
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

	@Transactional
	public void remove(EconomicActivityFarmer municipio) {
		economicActivityRepository.delete(municipio);
	}
	
}
