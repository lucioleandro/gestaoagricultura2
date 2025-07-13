package br.com.smart4.gestaoagriculturaapi.autenticacao.repositories;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

	List<Permission> findByPerfilId(Long perfilId);

}
