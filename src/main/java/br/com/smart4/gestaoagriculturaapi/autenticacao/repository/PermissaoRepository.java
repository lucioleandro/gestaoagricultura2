package br.com.smart4.gestaoagriculturaapi.autenticacao.repository;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

	List<Permissao> findByPerfilId(Long perfilId);

}
