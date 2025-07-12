package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.Produto;
import br.com.smart4.gestaoagriculturaapi.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraProduto(@Valid @RequestBody Produto request) {
		try {
			return ResponseEntity.created(null).body(produtoService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaProduto(@RequestBody Produto request) {
		try {
			return ResponseEntity.ok().body(produtoService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<Produto> getListaProdutoes() {
		try {
			return produtoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeProduto(@PathVariable Long id) {
		try {
			Optional<Produto> produto = produtoService.findById(id);

			if (produto.isPresent()) {
				produtoService.remove(produto.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
