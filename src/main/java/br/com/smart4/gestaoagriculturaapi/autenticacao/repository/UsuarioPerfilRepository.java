package br.com.smart4.gestaoagriculturaapi.autenticacao.repository;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long>{
	
	List<UsuarioPerfil> findByUsuarioId(Long id);

}
