package com.insper.prova;

import java.time.LocalDateTime;

public class Evento {
    private Long id;
    private String nome;
    private String local;
    private LocalDateTime dataHora;
    private Integer capacidade;
    //mesma ideia feita na aps e no model do participante!
    private boolean deleted;

    public Evento() {
        this.deleted = false;
    }

    public Evento(Long id, String nome, String local, LocalDateTime dataHora, Integer capacidade) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.dataHora = dataHora;
        this.capacidade = capacidade;
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}