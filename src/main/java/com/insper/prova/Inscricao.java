package com.insper.prova;


import java.time.LocalDateTime;

public class Inscricao {
    private Long id;
    private LocalDateTime dataInscricao;
    private boolean deleted;


    private Participante participante;
    private Evento evento;

    public Inscricao() {
        this.deleted = false;
    }

    public Inscricao(Long id, LocalDateTime dataInscricao, Participante participante, Evento evento) {
        this.id = id;
        this.dataInscricao = dataInscricao;
        this.participante = participante;
        this.evento = evento;
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}