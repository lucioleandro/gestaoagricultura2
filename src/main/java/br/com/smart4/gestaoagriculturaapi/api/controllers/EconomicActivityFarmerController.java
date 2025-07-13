package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityFarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.EconomicActivityFarmerService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/economic-activities-farmer")
public class EconomicActivityFarmerController {

    private final EconomicActivityFarmerService economicActivityFarmerService;

    public EconomicActivityFarmerController(EconomicActivityFarmerService economicActivityFarmerService) {
        this.economicActivityFarmerService = economicActivityFarmerService;
    }

    @PostMapping
    public ResponseEntity<?> cadastraEconomicActivityFarmer(@RequestBody @Valid EconomicActivityFarmerRequest request) {
//        if (request.isPrincipal() && existeAtividadePrincipal(request.getProperty())) {
//            return ResponseEntity.badRequest()
//                    .body(new ResponseMessage("Já existe uma atividade como principal"));
//        }
        // TODO revisar acima

        return ResponseEntity.created(null).body(economicActivityFarmerService.create(request));
    }

    private boolean existeAtividadePrincipal(Property property) {
        List<EconomicActivityFarmer> atividades = economicActivityFarmerService
                .findByProperty(property.getId());
        for (EconomicActivityFarmer atv : atividades) {
            if (atv.isPrincipal()) {
                return true;
            }
        }
        return false;
    }

    @PutMapping
    public ResponseEntity<?> atualizaEconomicActivityFarmer(@RequestBody @Valid EconomicActivityFarmerRequest request) {
//        if (request.isPrincipal() && existeAtividadePrincipal(request.getProperty())) {
//            return ResponseEntity.badRequest()
//                    .body(new ResponseMessage("Já existe uma atividade como principal"));
//        }
        // TODO revisar acima

        return ResponseEntity.ok().body(economicActivityFarmerService.atualiza(request));
    }

    @GetMapping
    public List<EconomicActivityFarmer> getListaEconomicActivity() {
        return economicActivityFarmerService.findAll();
    }

    @GetMapping("/farmer")
    public List<EconomicActivityFarmer> getListaAtividadeByFarmer(@Param(value = "id") Long id) {
        return economicActivityFarmerService.findByFarmer(id);
    }

    @GetMapping("/property")
    @ResponseBody
    public List<EconomicActivityFarmer> getListaAtividadeByProperty(@Param(value = "id") Long id) {
        return economicActivityFarmerService.findByProperty(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEconomicActivityFarmer(@PathVariable Long id) {
        try {
            Optional<EconomicActivityFarmer> economicActivityFarmer = economicActivityFarmerService.findById(id);

            if (economicActivityFarmer.isPresent()) {
                economicActivityFarmerService.remove(economicActivityFarmer.get());
                return ResponseEntity.ok().body("");
            }

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
