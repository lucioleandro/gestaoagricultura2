package br.com.smart4.gestaoagriculturaapi.autenticacao.repository;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permission, Long>{

	List<Permission> findByPerfilId(Long perfilId);

}
