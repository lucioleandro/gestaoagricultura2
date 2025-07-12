package br.com.smart4.gestaoagriculturaapi.api.service;

import br.com.smart4.gestaoagriculturaapi.api.domain.ImagemProduto;
import br.com.smart4.gestaoagriculturaapi.api.repository.ImagemProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagemProdutoService {

	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;
	
	public ImagemProduto create(ImagemProduto imagemProduto) {
		return imagemProdutoRepository.save(imagemProduto);
	}

	public ImagemProduto atualiza(ImagemProduto imagemProduto) {
		return imagemProdutoRepository.save(imagemProduto);
	}

	public Optional<ImagemProduto> findById(Long id) {
		return imagemProdutoRepository.findById(id);
	}

	public List<ImagemProduto> findAll() {
		return imagemProdutoRepository.findAll();
	}

	public List<ImagemProduto> findByProdutoId(Long id) {
		return imagemProdutoRepository.findByProdutoId(id);
	}

	public void remove(ImagemProduto imagemProduto) {
		imagemProdutoRepository.delete(imagemProduto);
	}
	
}
