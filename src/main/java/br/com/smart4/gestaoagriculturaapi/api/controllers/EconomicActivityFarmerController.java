package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityFarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityFarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.EconomicActivityFarmerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Economic Activities - Farmer", description = "Endpoints for managing economic activities related to farmers")
@RestController
@RequestMapping("/economic-activities-farmer")
public class EconomicActivityFarmerController {

    private final EconomicActivityFarmerService economicActivityFarmerService;

    public EconomicActivityFarmerController(EconomicActivityFarmerService economicActivityFarmerService) {
        this.economicActivityFarmerService = economicActivityFarmerService;
    }

    @Operation(summary = "Create a farmer economic activity", description = "Registers a new economic activity related to a farmer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<EconomicActivityFarmerResponse> create(@RequestBody @Valid EconomicActivityFarmerRequest request) {
//        if (request.isPrincipal() && existeAtividadePrincipal(request.getProperty())) {
//            return ResponseEntity.badRequest()
//                    .body(new ResponseMessage("Já existe uma atividade como principal"));
//        }
        // TODO revisar acima
        return ResponseEntity.created(null).body(economicActivityFarmerService.create(request));
    }

    private boolean existeAtividadePrincipal(Property property) {
        List<EconomicActivityFarmerResponse> atividades = economicActivityFarmerService
                .findByProperty(property.getId());
        for (EconomicActivityFarmerResponse atv : atividades) {
            if (atv.isPrincipal()) {
                return true;
            }
        }
        return false;
    }

    @Operation(summary = "Update a farmer economic activity", description = "Updates an existing economic activity related to a farmer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EconomicActivityFarmerResponse> update(@PathVariable Long id, @RequestBody @Valid EconomicActivityFarmerRequest request) {
//        if (request.isPrincipal() && existeAtividadePrincipal(request.getProperty())) {
//            return ResponseEntity.badRequest()
//                    .body(new ResponseMessage("Já existe uma atividade como principal"));
//        }
        // TODO revisar acima
        return ResponseEntity.ok().body(economicActivityFarmerService.update(id, request));
    }

    @Operation(summary = "Get all farmer economic activities", description = "Returns a list of all economic activities linked to farmers")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    public ResponseEntity<List<EconomicActivityFarmerResponse>> getList() {
        return ResponseEntity.ok(economicActivityFarmerService.findAll());
    }

    @Operation(summary = "Get farmer economic activities by farmer ID", description = "Returns all economic activities associated with a specific farmer")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping("/farmer")
    public ResponseEntity<List<EconomicActivityFarmerResponse>> getListByFarmer(@Param(value = "id") Long id) {
        return ResponseEntity.ok(economicActivityFarmerService.findByFarmer(id));
    }

    @Operation(summary = "Get farmer economic activities by property ID", description = "Returns all economic activities associated with a specific property")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping("/property")
    @ResponseBody
    public ResponseEntity<List<EconomicActivityFarmerResponse>> getListByProperty(@Param(value = "id") Long id) {
        return ResponseEntity.ok(economicActivityFarmerService.findByProperty(id));
    }

    @Operation(summary = "Delete a farmer economic activity", description = "Deletes an economic activity associated with a farmer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Activity deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        try {
//            Optional<EconomicActivityFarmer> economicActivityFarmer = economicActivityFarmerService.findById(id);
//
//            if (economicActivityFarmer.isPresent()) {
//                economicActivityFarmerService.remove(economicActivityFarmer.get());
//                return ResponseEntity.ok().body("");
//            }

            // TODO Levar lógica para o service

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
