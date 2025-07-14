package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.TitleDeedEnum;
import br.com.smart4.gestaoagriculturaapi.api.domains.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.TitleDeedRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.TitleDeedResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.PropertyService;
import br.com.smart4.gestaoagriculturaapi.api.services.TitleDeedService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
    public ResponseEntity<TitleDeedResponse> create(@RequestBody @Valid TitleDeedRequest request) throws IOException {
        TitleDeedResponse created = titleDeedService.create(request);
        return ResponseEntity.created(URI.create("/title-deeds/" + created.getId())).body(created);
    }

    @PutMapping
    public ResponseEntity<TitleDeedResponse> update(@RequestBody @Valid TitleDeedRequest request) throws IOException {
        return ResponseEntity.ok(titleDeedService.update(request));
    }

    @GetMapping
    public ResponseEntity<List<TitleDeedResponse>> getList() {
        return ResponseEntity.ok(titleDeedService.findAll());
    }

    @GetMapping("/property")
    public ResponseEntity<List<TitleDeedResponse>> getListByProperty(@Param("id") Long propertyId) {
        return ResponseEntity.ok(titleDeedService.findByProperty(propertyId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
//        // TODO: mover a lógica de verificação para o service
//        Optional<TitleDeedResponse> optional = titleDeedService.findResponseById(id);
//        if (optional.isPresent()) {
//            titleDeedService.remove(id);
//            return ResponseEntity.ok().build();
//        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<TitleDeedResponse> createPropertyDocuments(
            @RequestParam("titulo") String titulo,
            @RequestParam("observacao") String observacao,
            @RequestParam("bytes") MultipartFile arquivoMult,
            @RequestParam("extensao") String extensao,
            @RequestParam("documento") TitleDeedEnum documento,
            @RequestParam("property") Long idProperty
    ) throws Exception {

//        Optional<TitleDeedResponse> created = propertyService.findById(idProperty)
//                .map(property -> {
//                    try {
//                        byte[] bytes = arquivoMult.getBytes();
//                        TitleDeedRequest request = new TitleDeedRequest(titulo, observacao, LocalDateTime.now(), bytes, extensao, documento, property);
//                        return Optional.of(titleDeedService.create(request));
//                    } catch (Exception e) {
//                        throw new RuntimeException("Erro ao processar upload", e);
//                    }
//                });

//        return created.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.badRequest().build());

        //TODO levar para o service
        return null;
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable Long id, HttpServletResponse response) throws Exception {
        Optional<TitleDeedResponse> optional = titleDeedService.findById(id);

        if (optional.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        TitleDeedResponse doc = optional.get();

        response.setContentType("application/" + doc.getExtensao());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + doc.getTitulo() + "\"");

        try (ServletOutputStream out = response.getOutputStream();
             InputStream in = new ByteArrayInputStream(doc.getBytes())) {

            byte[] buffer = new byte[4096];
            int nRead;
            while ((nRead = in.read(buffer)) >= 0) {
                out.write(buffer, 0, nRead);
            }
            out.flush();
        }
    }
}
