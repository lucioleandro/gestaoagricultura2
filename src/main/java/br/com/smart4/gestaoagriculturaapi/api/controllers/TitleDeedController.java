package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.TitleDeedEnum;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.domains.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.TitleDeedRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.TitleDeedService;
import br.com.smart4.gestaoagriculturaapi.api.services.PropertyService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/title-deeds")
public class TitleDeedController {

    private final TitleDeedService titleDeedService;

    private final PropertyService propertyService;

    public TitleDeedController(TitleDeedService titleDeedService, PropertyService propertyService) {
        this.titleDeedService = titleDeedService;
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<?> cadastraDocumentosProperty(@RequestBody @Valid TitleDeedRequest request) throws IOException {
        return ResponseEntity.created(null).body(titleDeedService.create(request));
    }

    @PutMapping
    public ResponseEntity<?> atualizaDocumentosProperty(@RequestBody @Valid TitleDeedRequest request) throws IOException {
        return ResponseEntity.ok().body(titleDeedService.atualiza(request));
    }

    @GetMapping
    public List<TitleDeed> getListaDocumentosPropertys() {
        return titleDeedService.findAll();
    }

    @GetMapping("/property")
    public List<TitleDeed> getListaDocumentosPropertysbyproperty(@Param(value = "id") Long propertyId) {
        return titleDeedService.findByProperty(propertyId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeDocumentosProperty(@PathVariable Long id) {
        Optional<TitleDeed> documentosProperty = titleDeedService.findById(id);

        if (documentosProperty.isPresent()) {
            titleDeedService.remove(documentosProperty.get());
            return ResponseEntity.ok().body("");
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/upload")
    public ResponseEntity<?> cadastraDocumentosProperty(
            @RequestParam("titulo") String titulo,
            @RequestParam("observacao") String observacao,
            @RequestParam("bytes") MultipartFile arquivoMult,
            @RequestParam("extensao") String extensao,
            @RequestParam("documento") TitleDeedEnum documento,
            @RequestParam("property") Long idProperty) throws IOException {


        byte[] byteArquivo = arquivoMult.getBytes();
        LocalDateTime dataAdicao = LocalDateTime.now();
        Optional<Property> prop = propertyService.findById(idProperty);

        TitleDeedRequest arquivo = new TitleDeedRequest(titulo, observacao, dataAdicao,
                byteArquivo, extensao, documento, prop.get());

        return ResponseEntity.ok().body(titleDeedService.create(arquivo));

    }

    @GetMapping("/download/{id}")
    public FileOutputStream download(@PathVariable Long id,
                                     HttpServletResponse response, HttpServletRequest request) throws IOException {

        Optional<TitleDeed> doc = titleDeedService.findById(id);

        TitleDeed documento;
        if (doc.isPresent()) {
            documento = doc.get();

            response.setContentType("application/" + documento.getExtensao());
            response.setHeader("Content-disposition", "attachment; filename=" + documento.getTitulo());
            ServletOutputStream saida = response.getOutputStream();
            InputStream in = new ByteArrayInputStream(documento.getBytes());

            byte[] buffer = new byte[documento.getBytes().length];
            int nLidos;

            while ((nLidos = in.read(buffer)) >= 0) {
                saida.write(buffer, 0, nLidos);
            }

            saida.flush();
            saida.close();
            in.close();
        }

        return null;
    }

}
