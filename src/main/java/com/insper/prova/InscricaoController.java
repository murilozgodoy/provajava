package com.insper.prova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @PostMapping("/inscricoes")
    @ResponseStatus(HttpStatus.CREATED)
    public Inscricao cadastrarInscricao(@RequestBody Inscricao inscricao) {
        return inscricaoService.cadastrarInscricao(inscricao);
    }

    @GetMapping("/inscricoes")
    public Collection<Inscricao> listarInscricoes() {
        return inscricaoService.listarInscricoes();
    }

    @GetMapping("/inscricoes/{id}")
    public Inscricao buscarInscricao(@PathVariable Long id) {
        return inscricaoService.buscarInscricao(id);
    }

    @PutMapping("/inscricoes/{id}")
    public Inscricao atualizarInscricao(@PathVariable Long id, @RequestBody Inscricao inscricao) {
        return inscricaoService.atualizarInscricao(id, inscricao);
    }

    @DeleteMapping("/inscricoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarInscricao(@PathVariable Long id) {
        inscricaoService.deletarInscricao(id);
    }
}