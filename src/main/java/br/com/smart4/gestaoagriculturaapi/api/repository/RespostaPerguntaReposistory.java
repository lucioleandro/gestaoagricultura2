package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.RespostaPadrao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaPerguntaReposistory extends JpaRepository<RespostaPadrao, Long>{

}
