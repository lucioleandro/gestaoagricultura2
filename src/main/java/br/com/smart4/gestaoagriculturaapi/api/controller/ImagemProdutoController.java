package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.ImagemProduto;
import br.com.smart4.gestaoagriculturaapi.api.domain.Produto;
import br.com.smart4.gestaoagriculturaapi.api.service.ImagemProdutoService;
import br.com.smart4.gestaoagriculturaapi.api.service.ProdutoService;
import br.com.smart4.gestaoagriculturaapi.api.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagemproduto")
public class ImagemProdutoController {
	
	@Autowired
	private ImagemProdutoService imagemProdutoService;
	
	@Autowired
	private ProdutoService produtoService;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraImagemProduto(
												   @RequestParam("arquivo") MultipartFile arquivo,
												   @RequestParam("extensao") String extensao,
												   @RequestParam("produto") Long idProduto) {

		try {
			Optional<Produto> produto = produtoService.findById(idProduto);
			
			if(produto.isPresent()) {
				return ResponseEntity.created(null).body(imagemProdutoService
												.create(new ImagemProduto(arquivo.getBytes(), 
													extensao, produto.get())));
			} else {
				return ResponseEntity.badRequest().body(new ResponseMessage("Este produto não existe na base"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaImagemProduto(@RequestBody ImagemProduto request) {
		try {
			return ResponseEntity.ok().body(imagemProdutoService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<ImagemProduto> getListaImagemProdutos() {
		try {
			return imagemProdutoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyproduto")
	public List<ImagemProduto> getListaImagemProdutosByMunicipio(@Param(value = "id") Long id) {
		try {
			return imagemProdutoService.findByProdutoId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeImagemProduto(@PathVariable Long id) {
		try {
			Optional<ImagemProduto> imagemProduto = imagemProdutoService.findById(id);

			if (imagemProduto.isPresent()) {
				imagemProdutoService.remove(imagemProduto.get());
				return ResponseEntity.ok().body("");
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
