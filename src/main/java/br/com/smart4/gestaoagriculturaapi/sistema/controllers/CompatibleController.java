package br.com.smart4.gestaoagriculturaapi.sistema.controllers;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Compatible;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.CompatibleResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.services.CompatibleService;
import br.com.smart4.gestaoagriculturaapi.sistema.services.ParametersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Tag(name = "Compatibility", description = "Endpoints for managing version and license compatibility between the frontend, backend, and database.")
@RestController
@RequestMapping("/compatible")
public class CompatibleController {

    @Value("${app.version}")
    private String versaoApp;

    private final CompatibleService compatibleService;
    private final ParametersService parametersService;

    public CompatibleController(CompatibleService compatibleService, ParametersService parametersService) {
        this.compatibleService = compatibleService;
        this.parametersService = parametersService;
    }

    @Operation(summary = "Create a compatibility entry")
    @PostMapping
    public ResponseEntity<CompatibleResponse> create(
            @Valid @RequestBody Compatible request) {

        CompatibleResponse created = compatibleService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }


    @Operation(summary = "Update a compatibility entry")
    @PutMapping
    public ResponseEntity<?> atualizaCompativeis(@RequestBody Compatible request) {
        return ResponseEntity.ok().body(compatibleService.update(request));
    }

    @Operation(summary = "Get all compatibility entries")
    @GetMapping
    public List<CompatibleResponse> getListaCompativeises() {
        return compatibleService.findAll();
    }

    @Operation(summary = "Delete a compatibility entry by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCompativeis(@PathVariable Long id) {
        compatibleService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Check if frontend and backend are compatible with the database")
    @GetMapping("/check")
    public ResponseEntity<?> verificaSeaplicacaoEBaseCompativeis(@Parameter(description = "Frontend version") @Param(value = "versaoFront") String versaoFront) {
        CompatibleResponse compatible = compatibleService.findAll().get(0);

        if (aplicacaoEBaseSaoCompativeis(compatible, versaoFront)) {
            return ResponseEntity.ok().body(true);
        }

        return ResponseEntity.ok().body(false);
    }

    private boolean aplicacaoEBaseSaoCompativeis(CompatibleResponse compatible, String versaoFront) {
        String versaoLiberada = compatible.getVersaoLiberada();

        String versaoDaBaseDeDados = versaoLiberada.substring(0, 3);
        String versaoDaAplicacao = this.versaoApp.substring(0, 3);
        String versaoDoFront = versaoFront.substring(0, 3);

        if (versaoDaAplicacao.equals(versaoDaBaseDeDados)
                && versaoDaAplicacao.equals(versaoDoFront)) {
            return true;
        }
        return false;
    }

    @Operation(summary = "Check if system is blocked due to expired license")
    @GetMapping("/check-defaulter")
    public ResponseEntity<?> verificaSeEstaInadimplente() throws ParseException {
        CompatibleResponse compatible = compatibleService.findAll().get(0);

        boolean senhaValida = this.verificarSenha(compatible.getCodSistema().toString());

        if (senhaValida) {
            return ResponseEntity.ok().body(false);
        }

        return ResponseEntity.ok().body(true);
    }

    private boolean verificarSenha(String idsis) throws ParseException {
        if (SenhaDoSistemaValida(idsis)) {
            return true;
        }
        return false;
    }

    private boolean SenhaDoSistemaValida(String idsis) throws ParseException {
        boolean resultado = true;

        //BUSCAR A SENHA NA BASE DE DADOS PARA O SISTEMA EM QUESTAO
        String senha = null;
        try {
            senha = compatibleService.findByCodSistema(Integer.valueOf(idsis))
                    .get().getSenhaDeLiberacao();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (senha == null) {
            senha = "9X9X2X000481X3X9X1XX";
        }

        resultado = MB_SenhaValida(senha, "MBS" + idsis);

        return resultado;
    }

    @Operation(summary = "Update the license release password")
    @PostMapping("/validasenhaliberacao")
    public ResponseEntity<?> validaSenha(@RequestBody String senha) {
        Integer codSistema = compatibleService.findAll().get(0).getCodSistema();
        atualizaSenha(codSistema.toString(), senha);
        return ResponseEntity.ok().body("");
    }

    private void atualizaSenha(String idSistema, String senhaDeLiberacao) {
        // ATUALIZAR A SENHA NA BASE DE DADOS E LIBERAR O SISTEMA
        Compatible compatibleAux = null;
        compatibleAux = compatibleService.findByCodSistema(Integer.valueOf(idSistema)).get();
        if (!senhaDeLiberacao.equals("")) {
            compatibleAux.setSenhaDeLiberacao(senhaDeLiberacao);
            compatibleService.update(compatibleAux);
        }
    }

    private boolean MB_SenhaValida(String PassWord, String NSis) throws ParseException {
        boolean resultado = true;
        boolean msgdoprogramador = false;
        Date DataV, DataI;
        Date agora = new Date();
        String IDCli, IDSis;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

        // VERIFICAR A VALIDADE DA SENHA
        // VERIFICAR SE É POSSIVEL DECODIFICAR A SENHA INFORMADA

        DataV = GetDV(PassWord);
        if (sdf1.parse("01/01/1950").equals(DataV)) {
            if (msgdoprogramador)
                System.out.println("Erro: Validando Data de Vencimento...");
            return false;
        }

        DataI = GetDI(PassWord);
        if (sdf1.parse("01/01/9999").equals(DataI)) {
            if (msgdoprogramador)
                System.out.println("Erro: Validando Data Inicial...");
            return false;
        }

        IDCli = GetIdCli(PassWord);
        if ("XXX".equals(IDCli)) {
            if (msgdoprogramador)
                System.out.println("Erro: Validando Id do Cliente...");
            return false;
        }

        IDSis = GetIdSis(PassWord);
        if ("XXX00".equals(IDSis)) {
            if (msgdoprogramador)
                System.out.println("Erro: Validando Id do Sistema...");
            return false;
        }

        // CONFERIR DATA DA SENHA COM DATA DO COMPUTADOR
        if (agora.before(DataI) || agora.after(DataV)) {
            if (msgdoprogramador)
                System.out.println("Erro: Comparando a data atual com o período da senha...");
            return false;
        }

        // CONFERIR SISTEMA
        if (!NSis.equals(IDSis)) {
            if (msgdoprogramador)
                System.out.println("Erro: Comparando o código do sistema com o código da senha...");
            return false;
        }

        // CONFERIR CLIENTE
        if (!parametersService.findAll().get(0).getAcod().equals(IDCli)) {
            if (msgdoprogramador)
                System.out.println("Erro: Comparando o código do cliente com o código da senha...");
            return false;
        }

        return resultado;
    }

    private Date GetDV(String PassWord) throws ParseException {
        Date resultado = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1950");
        Integer DiaV, MesV, AnoV;
        int LetCod;
        char[] MesFull = {'X', 'X', 'X'};
        String MesFullTemp;
        String SenhaCod = PassWord;

        DiaV = Integer.parseInt(SenhaCod.substring(0, 1), 10) + Integer.parseInt(SenhaCod.substring(8, 9), 10) * 10;

        LetCod = SenhaCod.charAt(1) - (DiaV % 10);
        if (LetCod < 65) LetCod += 26;
        MesFull[0] = (char) LetCod;

        LetCod = SenhaCod.charAt(3) - (DiaV % 10);
        if (LetCod < 65) LetCod += 26;
        MesFull[1] = (char) LetCod;

        LetCod = SenhaCod.charAt(5) - (DiaV % 10);
        if (LetCod < 65) LetCod += 26;
        MesFull[2] = (char) LetCod;

        MesFullTemp = String.valueOf(MesFull);

        MesV = switch (MesFullTemp) {
            case "JAN" -> 1;
            case "FEV" -> 2;
            case "MAR" -> 3;
            case "ABR" -> 4;
            case "MAI" -> 5;
            case "JUN" -> 6;
            case "JUL" -> 7;
            case "AGO" -> 8;
            case "SET" -> 9;
            case "OUT" -> 10;
            case "NOV" -> 11;
            case "DEZ" -> 12;
            default -> 0;
        };

        if (MesV == 0) return resultado;

        AnoV = 2000 + (Integer.parseInt(SenhaCod.substring(7, 8)) * 100
                + Integer.parseInt(SenhaCod.substring(4, 5)) * 10
                + Integer.parseInt(SenhaCod.substring(2, 3))) - DiaV - MesV;

        return new SimpleDateFormat("dd/MM/yyyy").parse(DiaV + "/" + MesV + "/" + AnoV);
    }

    @SuppressWarnings("deprecation")
    private Date GetDI(String PassWord) throws ParseException {
        Date DataV = GetDV(PassWord);
        int DiaV = DataV.getDate();
        int Validade;

        if (DiaV % 10 > 5)
            Validade = Integer.parseInt(PassWord.substring(9, 10)) * 10 + Integer.parseInt(PassWord.substring(6, 7));
        else
            Validade = Integer.parseInt(PassWord.substring(6, 7)) * 10 + Integer.parseInt(PassWord.substring(9, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DataV);
        calendar.add(Calendar.DAY_OF_YEAR, -Validade);
        return calendar.getTime();
    }

    private String GetIdCli(String PassWord) {
        int DigC = Integer.parseInt(PassWord.substring(10, 11));
        char[] CliFull = {
                (char) (PassWord.charAt(19) - DigC < 65 ? PassWord.charAt(19) - DigC + 26 : PassWord.charAt(19) - DigC),
                (char) (PassWord.charAt(12) - DigC < 65 ? PassWord.charAt(12) - DigC + 26 : PassWord.charAt(12) - DigC),
                (char) (PassWord.charAt(15) - DigC < 48 ? PassWord.charAt(15) - DigC + 10 : PassWord.charAt(15) - DigC)
        };
        return String.valueOf(CliFull);
    }

    private String GetIdSis(String PassWord) {
        int DigS = Integer.parseInt(PassWord.substring(11, 12));
        char[] SisFull = {
                (char) (PassWord.charAt(14) - DigS < 65 ? PassWord.charAt(14) - DigS + 26 : PassWord.charAt(14) - DigS),
                (char) (PassWord.charAt(16) - DigS < 65 ? PassWord.charAt(16) - DigS + 26 : PassWord.charAt(16) - DigS),
                (char) (PassWord.charAt(18) - DigS < 65 ? PassWord.charAt(18) - DigS + 26 : PassWord.charAt(18) - DigS),
                (char) (PassWord.charAt(17) - DigS < 48 ? PassWord.charAt(17) - DigS + 10 : PassWord.charAt(17) - DigS),
                (char) (PassWord.charAt(13) - DigS < 48 ? PassWord.charAt(13) - DigS + 10 : PassWord.charAt(13) - DigS)
        };
        return String.valueOf(SisFull);
    }
}
