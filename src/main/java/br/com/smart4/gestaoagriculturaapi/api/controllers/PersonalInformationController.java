package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PersonalInformationRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PersonalInformationResponse;
import br.com.smart4.gestaoagriculturaapi.api.services.PersonalInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Personal Information", description = "Endpoints for managing personal information of individuals")
@RestController
@RequestMapping("/personal-information")
public class PersonalInformationController {

    private final PersonalInformationService personalInformationService;

    public PersonalInformationController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @Operation(summary = "Create personal information", description = "Registers a new personal information record in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Personal information created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<PersonalInformationResponse> create(
            @RequestBody @Valid PersonalInformationRequest request) {

        PersonalInformationResponse created = personalInformationService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }


    @Operation(summary = "Update personal information", description = "Updates an existing personal information record by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal information updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Record not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PersonalInformationResponse> update(@PathVariable Long id, @RequestBody @Valid PersonalInformationRequest request) {
        PersonalInformationResponse response = personalInformationService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "List all personal information records", description = "Retrieves all personal information records stored in the system")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    public ResponseEntity<List<PersonalInformationResponse>> getList() {
        List<PersonalInformationResponse> responses = personalInformationService.findAll();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Delete a personal information record", description = "Deletes a personal information record by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Record deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Record not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        personalInformationService.remove(id);
        return ResponseEntity.notFound().build();
    }
}
