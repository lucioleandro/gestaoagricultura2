package br.com.smart4.gestaoagriculturaapi.autenticacao.repository;


import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	@Query(value = "SELECT  * FROM aut_perfil perfil "
			+ "LEFT JOIN aut_usuarioperfil usuarioperfil "
			+ "ON (usuarioperfil.perfil_id = perfil.id) "
			+ "WHERE usuarioperfil.usuario_id = :id", 
			nativeQuery = true)
	List<Perfil> findByIdUsuario(Long id);
}
