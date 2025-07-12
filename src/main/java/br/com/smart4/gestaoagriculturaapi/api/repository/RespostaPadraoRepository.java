package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.RespostaPadrao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaPadraoRepository extends JpaRepository<RespostaPadrao, Long> {

	List<RespostaPadrao> findByPerguntaId(Long id);

}
