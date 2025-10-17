package com.lucasmartins.mineiracaodados.service;

import com.lucasmartins.mineiracaodados.model.Turma;
import com.lucasmartins.mineiracaodados.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository repo;

    public TurmaService(TurmaRepository repo) {
        this.repo = repo;
    }

    public Turma criar(Turma turma) {
        turma.setId(null);
        return repo.save(turma);
    }

    public Turma buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Turma> listarTodas() {
        return repo.findAll();
    }

    public Turma atualizar(Long id, Turma dados) {
        return repo.findById(id).map(t -> {
            t.setNome(dados.getNome());
            return repo.save(t);
        }).orElse(null);
    }

    public boolean excluir(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
