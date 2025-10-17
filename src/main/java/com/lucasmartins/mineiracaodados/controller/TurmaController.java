package com.lucasmartins.mineiracaodados.controller;

import com.lucasmartins.mineiracaodados.model.Turma;
import com.lucasmartins.mineiracaodados.service.TurmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    // Criar
    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody Turma turma) {
        Turma criada = service.criar(turma);
        return ResponseEntity.created(URI.create("/api/turmas/" + criada.getId())).body(criada);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        Turma turma = service.buscarPorId(id);
        return (turma == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(turma);
    }

    // Listar todas
    @GetMapping
    public List<Turma> listarTodas() {
        return service.listarTodas();
    }

    // Atualizar por ID
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma dados) {
        Turma atualizada = service.atualizar(id, dados);
        return (atualizada == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(atualizada);
    }

    // Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
