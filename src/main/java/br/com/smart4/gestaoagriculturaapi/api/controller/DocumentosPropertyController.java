package br.com.smart4.gestaoagriculturaapi.api.controller;

import br.com.smart4.gestaoagriculturaapi.api.domain.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.domain.EnumTitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.domain.Property;
import br.com.smart4.gestaoagriculturaapi.api.service.DocumentosPropertyService;
import br.com.smart4.gestaoagriculturaapi.api.service.PropertyService;
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
public class DocumentosPropertyController {
	
	@Autowired
	private DocumentosPropertyService documentosPropertyService;
	
	@Autowired
	private PropertyService propertyService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraDocumentosProperty(@RequestBody TitleDeed request) {
		try {
			return ResponseEntity.created(null).body(documentosPropertyService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaDocumentosProperty(@RequestBody TitleDeed request) {
		try {	
			return ResponseEntity.ok().body(documentosPropertyService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<TitleDeed> getListaDocumentosPropertys() {
		try {
			return documentosPropertyService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/listabyproperty")
	public List<TitleDeed> getListaDocumentosPropertysbyproperty(@Param(value = "id")Long id) {
		try {
			return documentosPropertyService.findByProperty(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeDocumentosProperty(@PathVariable Long id) {
		try {
			Optional<TitleDeed> documentosProperty = documentosPropertyService.findById(id);

			if (documentosProperty.isPresent()) {
				documentosPropertyService.remove(documentosProperty.get());
				return ResponseEntity.ok().body("");
			} 

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> cadastraDocumentosProperty(
			@RequestParam("titulo") String titulo,
	    	@RequestParam("observacao") String observacao,
			@RequestParam("bytes") MultipartFile arquivoMult,
			@RequestParam("extensao") String extensao,
			@RequestParam("documento") EnumTitleDeed documento,
			@RequestParam("property") Long idProperty) {
		

		try {
			byte[] byteArquivo = arquivoMult.getBytes();
			LocalDateTime dataAdicao = LocalDateTime.now();
			Optional<Property> prop = propertyService.findById(idProperty);
			
			TitleDeed arquivo = new TitleDeed(titulo, observacao, dataAdicao, 
					byteArquivo, extensao, documento, prop.get());
			
			return ResponseEntity.ok().body(documentosPropertyService.create(arquivo));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/download/{id}")
	public FileOutputStream download(@PathVariable Long id,
									 HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		Optional<TitleDeed> doc = documentosPropertyService.findById(id);
		
		TitleDeed documento;
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
