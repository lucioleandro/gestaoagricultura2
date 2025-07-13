package br.com.smart4.gestaoagriculturaapi.autenticacao.repositories;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	
	List<UserProfile> findByUsuarioId(Long id);

}
