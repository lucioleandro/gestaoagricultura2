package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.ImagemProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {

	List<ImagemProduto> findByProdutoId(Long id);

}
