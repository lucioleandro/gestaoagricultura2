package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.Produto;
import br.com.smart4.gestaoagriculturaapi.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto create(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto atualiza(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Optional<Produto> findById(Long id) {
		return produtoRepository.findById(id);
	}

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public void remove(Produto produto) {
		produtoRepository.delete(produto);
	}
	
}
