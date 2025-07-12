package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
