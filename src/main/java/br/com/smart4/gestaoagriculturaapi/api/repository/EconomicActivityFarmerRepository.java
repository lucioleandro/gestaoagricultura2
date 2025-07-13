package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.EconomicActivityFarmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EconomicActivityFarmerRepository extends JpaRepository<EconomicActivityFarmer, Long>{

	List<EconomicActivityFarmer> findByFarmerId(Long id);

	List<EconomicActivityFarmer> findByPropertyId(Long id);

}
