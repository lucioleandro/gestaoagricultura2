package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.services.LivestockActivityService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livestockactivity")
public class LivestockActivityController {

    private final LivestockActivityService livestockActivityService;

    public LivestockActivityController(LivestockActivityService livestockActivityService) {
        this.livestockActivityService = livestockActivityService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraLivestockActivity(@RequestBody LivestockActivity request) {
        return ResponseEntity.created(null).body(livestockActivityService.create(request));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaLivestockActivity(@RequestBody LivestockActivity request) {
        return ResponseEntity.ok().body(livestockActivityService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<LivestockActivity> getListaLivestockActivity() {
        return livestockActivityService.findAll();
    }

    @GetMapping("/listabyproperty")
    public List<LivestockActivity> getListaLivestockActivityByProperty(@Param(value = "id") Long id) {
        return livestockActivityService.findByPropertyId(id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeLivestockActivity(@PathVariable Long id) {
        Optional<LivestockActivity> livestockActivity = livestockActivityService.findById(id);

        if (livestockActivity.isPresent()) {
            livestockActivityService.remove(livestockActivity.get());
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.notFound().build();

    }
}

