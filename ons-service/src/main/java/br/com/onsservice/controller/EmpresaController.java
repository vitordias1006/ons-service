package br.com.onsservice.controller;

import br.com.onsservice.domain.Empresa;
import br.com.onsservice.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Empresa> salvarEmpresa(@RequestBody Empresa empresa) {
        empresaService.salvarEmpresa(empresa);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Empresa> buscarEmpresaPorId(@RequestParam Long id) {
        return ResponseEntity.ok(empresaService.buscarEmpresaPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresaPorId(@RequestParam Long id) {
        empresaService.excluirEmpresaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        empresaService.atualizarEmpresaPorId(id, empresa);
        return ResponseEntity.ok().build();
    }
}
