package br.com.smart4.gestaoagriculturaapi.autenticacao.repositories;


import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	@Query(value = "SELECT perfil.* FROM aut_profile perfil " +
			"LEFT JOIN aut_user_profile usuarioperfil " +
			"ON usuarioperfil.perfil_id = perfil.id " +
			"WHERE usuarioperfil.usuario_id = :id",
			nativeQuery = true)
	List<Profile> findByUserId(Long id);

}
