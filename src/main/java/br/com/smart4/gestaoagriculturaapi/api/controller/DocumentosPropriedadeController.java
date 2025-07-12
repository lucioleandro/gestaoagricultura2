package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.DocumentoPropriedade;
import br.com.smart4.gestaoagriculturaapi.api.domain.EnumDocumentosPropriedade;
import br.com.smart4.gestaoagriculturaapi.api.domain.Propriedade;
import br.com.smart4.gestaoagriculturaapi.api.service.DocumentosPropriedadeService;
import br.com.smart4.gestaoagriculturaapi.api.service.PropriedadeService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documentos")
public class DocumentosPropriedadeController {
	
	@Autowired
	private DocumentosPropriedadeService documentosPropriedadeService;
	
	@Autowired
	private PropriedadeService propriedadeService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraDocumentosPropriedade(@RequestBody DocumentoPropriedade request) {
		try {
			return ResponseEntity.created(null).body(documentosPropriedadeService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaDocumentosPropriedade(@RequestBody DocumentoPropriedade request) {
		try {	
			return ResponseEntity.ok().body(documentosPropriedadeService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<DocumentoPropriedade> getListaDocumentosPropriedades() {
		try {
			return documentosPropriedadeService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabypropriedade")
	public List<DocumentoPropriedade> getListaDocumentosPropriedadesbypropriedade(@Param(value = "id")Long id) {
		try {
			return documentosPropriedadeService.findByPropriedade(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeDocumentosPropriedade(@PathVariable Long id) {
		try {
			Optional<DocumentoPropriedade> documentosPropriedade = documentosPropriedadeService.findById(id);

			if (documentosPropriedade.isPresent()) {
				documentosPropriedadeService.remove(documentosPropriedade.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> cadastraDocumentosPropriedade(
			@RequestParam("titulo") String titulo,
	    	@RequestParam("observacao") String observacao,
			@RequestParam("bytes") MultipartFile arquivoMult,
			@RequestParam("extensao") String extensao,
			@RequestParam("documento") EnumDocumentosPropriedade documento,
			@RequestParam("propriedade") Long idPropriedade) {
		

		try {
			byte[] byteArquivo = arquivoMult.getBytes();
			LocalDateTime dataAdicao = LocalDateTime.now();
			Optional<Propriedade> prop = propriedadeService.findById(idPropriedade);
			
			DocumentoPropriedade arquivo = new DocumentoPropriedade(titulo, observacao, dataAdicao, 
					byteArquivo, extensao, documento, prop.get());
			
			return ResponseEntity.ok().body(documentosPropriedadeService.create(arquivo));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/download/{id}")
	public FileOutputStream download(@PathVariable Long id,
									 HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		Optional<DocumentoPropriedade> doc = documentosPropriedadeService.findById(id);
		
		DocumentoPropriedade documento;
		if(doc.isPresent()) {
			documento = doc.get();
			
			response.setContentType("application/" + documento.getExtensao());
			response.setHeader("Content-disposition", "attachment; filename="+documento.getTitulo());
			ServletOutputStream saida = response.getOutputStream();
			InputStream in = new ByteArrayInputStream(documento.getBytes());

			byte[] buffer = new byte[documento.getBytes().length];
			int nLidos;
			
			while((nLidos = in.read(buffer)) >= 0) {
				saida.write(buffer, 0, nLidos);
			}
				
			saida.flush();
			saida.close();
			in.close();
		}
		
		return null;
	}

}
