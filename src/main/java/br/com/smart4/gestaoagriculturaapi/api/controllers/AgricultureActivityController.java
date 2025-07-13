package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.services.AgricultureActivityService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agricultureactivity")
public class AgricultureActivityController {

    private final AgricultureActivityService agricultureActivityService;

    public AgricultureActivityController(AgricultureActivityService agricultureActivityService) {
        this.agricultureActivityService = agricultureActivityService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraAgricultureActivity(@RequestBody AgricultureActivity request) {
        return ResponseEntity.created(null).body(agricultureActivityService.create(request));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaAgricultureActivity(@RequestBody AgricultureActivity request) {
        agricultureActivityService.atualiza(request);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/lista")
    public List<AgricultureActivity> getListaAgricultureActivity() {
        return agricultureActivityService.findAll();
    }

    @GetMapping("/listabyproperty")
    @ResponseBody
    public List<AgricultureActivity> getListaAgricultureActivityByProperty(@Param(value = "id") Long id) {
        return agricultureActivityService.findByProperty(id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeAgricultureActivity(@PathVariable Long id) {
        Optional<AgricultureActivity> agricultureActivity = agricultureActivityService.findById(id);

        if (agricultureActivity.isPresent()) {
            agricultureActivityService.remove(agricultureActivity.get());
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.notFound().build();
    }

}
