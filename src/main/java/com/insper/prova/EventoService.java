package com.insper.prova;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EventoService {

    private Map<Long, Evento> eventos = new HashMap<>();
    private Long proximoId = 1L;

    public Evento cadastrarEvento(Evento evento) {
        if (evento == null) {
            throw new RuntimeException("Evento não pode ser nulo");
        }

        if (evento.getNome() == null || evento.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        if (evento.getLocal() == null || evento.getLocal().isBlank()) {
            throw new RuntimeException("Local é obrigatório");
        }

        if (evento.getDataHora() == null) {
            throw new RuntimeException("Data e hora são obrigatórias");
        }

        if (evento.getCapacidade() == null || evento.getCapacidade() <= 0) {
            throw new RuntimeException("Capacidade deve ser maior que zero");
        }

        evento.setId(proximoId);
        proximoId++;

        eventos.put(evento.getId(), evento);
        return evento;
    }

    public Collection<Evento> listarEventos() {
        Collection<Evento> ativos = new ArrayList<>();

        for (Evento evento : eventos.values()) {
            if (!evento.isDeleted()) {
                ativos.add(evento);
            }
        }

        return ativos;
    }

    public Evento buscarEvento(Long id) {
        Evento evento = eventos.get(id);

        if (evento == null || evento.isDeleted()) {
            throw new RuntimeException("Evento não encontrado");
        }

        return evento;
    }

    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        Evento eventoExistente = buscarEvento(id);

        if (eventoAtualizado == null) {
            throw new RuntimeException("Evento não pode ser nulo");
        }

        if (eventoAtualizado.getNome() == null || eventoAtualizado.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        if (eventoAtualizado.getLocal() == null || eventoAtualizado.getLocal().isBlank()) {
            throw new RuntimeException("Local é obrigatório");
        }

        if (eventoAtualizado.getDataHora() == null) {
            throw new RuntimeException("Data e hora são obrigatórias");
        }

        if (eventoAtualizado.getCapacidade() == null || eventoAtualizado.getCapacidade() <= 0) {
            throw new RuntimeException("Capacidade deve ser maior que zero");
        }

        eventoExistente.setNome(eventoAtualizado.getNome());
        eventoExistente.setLocal(eventoAtualizado.getLocal());
        eventoExistente.setDataHora(eventoAtualizado.getDataHora());
        eventoExistente.setCapacidade(eventoAtualizado.getCapacidade());

        return eventoExistente;
    }

    public void deletarEvento(Long id) {
        Evento evento = buscarEvento(id);
        evento.setDeleted(true);
    }
}