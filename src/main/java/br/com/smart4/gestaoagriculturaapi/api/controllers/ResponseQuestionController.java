package br.com.smart4.gestaoagriculturaapi.api.controllers;

import br.com.smart4.gestaoagriculturaapi.api.domains.ResponseQuestion;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ResponseQuestionRequest;
import br.com.smart4.gestaoagriculturaapi.api.services.ResponseQuestionService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respostapergunta")
public class ResponseQuestionController {

    private final ResponseQuestionService respostaQuestionService;

    public ResponseQuestionController(ResponseQuestionService respostaQuestionService) {
        this.respostaQuestionService = respostaQuestionService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<?> cadastraResponseQuestion(@RequestBody @Valid ResponseQuestionRequest request) {
        return ResponseEntity.created(null).body(respostaQuestionService.create(request));
    }

    @PostMapping("/cadastrarespostas")
    public ResponseEntity<?> cadastraRespostasQuestion(@RequestBody @Valid  List<ResponseQuestionRequest> request) {
//        Long idFarmer = request.get(0).getFarmer().getId();
//        this.DeletaRespostasMultiplaEscolhaByFarmer(idFarmer);
        // TODO revisar isso aqui

        for (ResponseQuestionRequest resposta : request) {
            respostaQuestionService.create(resposta);
        }

        return ResponseEntity.ok().body("");
    }

    private void DeletaRespostasMultiplaEscolhaByFarmer(Long id) {
        respostaQuestionService.removeRespostasMultiplaEscolhaByFarmer(id);
    }

    @PutMapping("/atualiza")
    public ResponseEntity<?> atualizaResponseQuestion(@RequestBody @Valid ResponseQuestionRequest request) {
        return ResponseEntity.ok().body(respostaQuestionService.atualiza(request));
    }

    @GetMapping("/lista")
    public List<ResponseQuestion> getListaResponseQuestiones() {
        return respostaQuestionService.findAll();
    }

    @GetMapping("/listabyfarmer")
    public List<ResponseQuestion> getListaResponseQuestionByQuestion(@Param(value = "id") Long id) {
        return respostaQuestionService.findByFarmerId(id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeResponseQuestion(@PathVariable Long id) {
        Optional<ResponseQuestion> respostaQuestion = respostaQuestionService.findById(id);

        if (respostaQuestion.isPresent()) {
            respostaQuestionService.remove(respostaQuestion.get());
            return ResponseEntity.ok().body("");
        }

        return ResponseEntity.notFound().build();
    }
}
