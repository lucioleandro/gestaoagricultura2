package br.com.smart4.gestaoagriculturaapi.api.repositories;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EconomicActivityFarmerRepository extends JpaRepository<EconomicActivityFarmer, Long>{

	List<EconomicActivityFarmer> findByFarmerId(Long id);

	List<EconomicActivityFarmer> findByPropertyId(Long id);

	boolean existsByPropertyIdAndPrincipalTrue(Long propertyId);

	boolean existsByPropertyIdAndPrincipalTrueAndIdNot(Long propertyId, Long id);

}
