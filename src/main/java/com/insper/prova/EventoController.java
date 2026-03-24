package com.insper.prova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Collection;

@RestController
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/eventos")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento cadastrarEvento(@RequestBody Evento evento) {
        return eventoService.cadastrarEvento(evento);
    }

    @GetMapping("/eventos")
    public Collection<Evento> listarEventos() {
        return eventoService.listarEventos();
    }

    @GetMapping("/eventos/{id}")
    public Evento buscarEvento(@PathVariable Long id) {
        return eventoService.buscarEvento(id);
    }

    @PutMapping("/eventos/{id}")
    public Evento atualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.atualizarEvento(id, evento);
    }

    @DeleteMapping("/eventos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEvento(@PathVariable Long id) {
        eventoService.deletarEvento(id);
    }
}