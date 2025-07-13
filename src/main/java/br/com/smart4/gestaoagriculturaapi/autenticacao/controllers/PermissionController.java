package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.api.utils.ResponseMessage;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Permission;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.PermissionService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<?> cadastraPermissao(@RequestBody Permission request) {
        if (componenteJaCadastradoParaOPerfil(request.getComponente(), request.getPerfil())) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessage("Este componente já está vinculado a este perfil !"));
        }
        return ResponseEntity.created(null).body(permissionService.create(request));
    }

    private boolean componenteJaCadastradoParaOPerfil(String componente, Profile perfil) {
        List<Permission> perfis = permissionService.findByPerfilId(perfil.getId());

        for (Permission perf : perfis) {
            if (perf.getComponente().equals(componente)) {
                return true;
            }
        }

        return false;
    }

    @PutMapping
    public ResponseEntity<?> atualizaPermissao(@RequestBody Permission request) {
        if (componenteJaCadastradoParaOPerfil(request.getComponente(), request.getPerfil())) {
            return ResponseEntity.badRequest().body(
                    new ResponseMessage("Este componente já está vinculado a este perfil!"));
        }
        return ResponseEntity.ok().body(permissionService.atualiza(request));
    }

    @GetMapping
    public List<Permission> getListaPermissao() {
        return permissionService.findAll();
    }

    @GetMapping("/profile")
    public List<Permission> getListaPermissao(@Param(value = "id") Long profileId) {
        return permissionService.findByPerfilId(profileId);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePermissao(@PathVariable Long id) {
        Optional<Permission> permissao = permissionService.findById(id);

        if (permissao.isPresent()) {
            permissionService.remove(permissao.get());
            return ResponseEntity.ok().body("");
        } else if (!permissao.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
