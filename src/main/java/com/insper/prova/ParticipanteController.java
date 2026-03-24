package com.insper.prova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import java.util.Collection;

@RestController
public class ParticipanteController {

    //jah que nos controllers precismamos dos respectivos services
    @Autowired
    private ParticipanteService participanteService;

    @PostMapping("/participantes")
    @ResponseStatus(HttpStatus.CREATED)
    public Participante cadastrarParticipante(@RequestBody Participante participante) {
        return participanteService.cadastrarParticipante(participante);
    }

    @GetMapping("/participantes")
    public Collection<Participante> listarParticipantes() {
        return participanteService.listarParticipantes();
    }

    @GetMapping("/participantes/{id}")
    public Participante buscarParticipante(@PathVariable Long id) {
        return participanteService.buscarParticipante(id);
    }

    @PutMapping("/participantes/{id}")
    public Participante atualizarParticipante(@PathVariable Long id, @RequestBody Participante participante) {
        return participanteService.atualizarParticipante(id, participante);
    }

    @DeleteMapping("/participantes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarParticipante(@PathVariable Long id) {
        participanteService.deletarParticipante(id);
    }
}