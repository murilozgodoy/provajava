package com.insper.prova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InscricaoService {

    private Map<Long, Inscricao> inscricoes = new HashMap<>();
    private Long proximoId = 1L;

    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private EventoService eventoService;

    public Inscricao cadastrarInscricao(Inscricao inscricao) {
        if (inscricao == null) {
            throw new RuntimeException("Inscrição não pode ser nula");
        }

        if (inscricao.getDataInscricao() == null) {
            throw new RuntimeException("Data da inscrição é obrigatória");
        }

        if (inscricao.getParticipante() == null || inscricao.getParticipante().getId() == null) {
            throw new RuntimeException("Participante é obrigatório");
        }

        if (inscricao.getEvento() == null || inscricao.getEvento().getId() == null) {
            throw new RuntimeException("Evento é obrigatório");
        }

        Participante participante = participanteService.buscarParticipante(inscricao.getParticipante().getId());
        Evento evento = eventoService.buscarEvento(inscricao.getEvento().getId());

        for (Inscricao i : inscricoes.values()) {
            if (!i.isDeleted()
                    && !i.getParticipante().isDeleted()
                    && !i.getEvento().isDeleted()
                    && i.getParticipante().getId().equals(participante.getId())
                    && i.getEvento().getId().equals(evento.getId())) {
                throw new RuntimeException("Participante já está inscrito neste evento");
            }
        }

        //pensei em fazer uma conta para saber o nmr de inscricoes para conseguir saber se o evento esta lotadod
        int totalInscricoesAtivas = 0;
        for (Inscricao i : inscricoes.values()) {
            if (!i.isDeleted()
                    && !i.getParticipante().isDeleted()
                    && !i.getEvento().isDeleted()
                    && i.getEvento().getId().equals(evento.getId())) {
                totalInscricoesAtivas++;
            }
        }
        //seria uma relacao cm o evento
        if (totalInscricoesAtivas >= evento.getCapacidade()) {
            throw new RuntimeException("Evento lotado");
        }

        inscricao.setId(proximoId++);
        inscricao.setParticipante(participante);
        inscricao.setEvento(evento);

        inscricoes.put(inscricao.getId(), inscricao);
        return inscricao;
    }

    public Collection<Inscricao> listarInscricoes() {
        Collection<Inscricao> ativas = new ArrayList<>();

        for (Inscricao inscricao : inscricoes.values()) {
            if (!inscricao.isDeleted()
                    && !inscricao.getParticipante().isDeleted()
                    && !inscricao.getEvento().isDeleted()) {
                ativas.add(inscricao);
            }
        }

        return ativas;
    }

    public Inscricao buscarInscricao(Long id) {
        Inscricao inscricao = inscricoes.get(id);

        if (inscricao == null
                || inscricao.isDeleted()
                || inscricao.getParticipante().isDeleted()
                || inscricao.getEvento().isDeleted()) {
            throw new RuntimeException("Inscrição não encontrada");
        }

        return inscricao;
    }

    public Inscricao atualizarInscricao(Long id, Inscricao inscricaoAtualizada) {
        Inscricao inscricaoExistente = buscarInscricao(id);

        if (inscricaoAtualizada == null) {
            throw new RuntimeException("Inscrição não pode ser nula");
        }

        if (inscricaoAtualizada.getDataInscricao() == null) {
            throw new RuntimeException("Data da inscrição é obrigatória");
        }

        if (inscricaoAtualizada.getParticipante() == null || inscricaoAtualizada.getParticipante().getId() == null) {
            throw new RuntimeException("Participante é obrigatório");
        }

        if (inscricaoAtualizada.getEvento() == null || inscricaoAtualizada.getEvento().getId() == null) {
            throw new RuntimeException("Evento é obrigatório");
        }

        Participante participante = participanteService.buscarParticipante(inscricaoAtualizada.getParticipante().getId());
        Evento evento = eventoService.buscarEvento(inscricaoAtualizada.getEvento().getId());

        for (Inscricao i : inscricoes.values()) {
            if (!i.isDeleted()
                    && !i.getParticipante().isDeleted()
                    && !i.getEvento().isDeleted()
                    && !i.getId().equals(id)
                    && i.getParticipante().getId().equals(participante.getId())
                    && i.getEvento().getId().equals(evento.getId())) {
                throw new RuntimeException("Participante já está inscrito neste evento");
            }
        }

        int totalInscricoesAtivas = 0;
        for (Inscricao i : inscricoes.values()) {
            if (!i.isDeleted() && !i.getParticipante().isDeleted() && !i.getEvento().isDeleted()
                    && i.getEvento().getId().equals(evento.getId())
                    && !i.getId().equals(id)) {
                totalInscricoesAtivas++;
            }
        }

        if (totalInscricoesAtivas >= evento.getCapacidade()) {
            throw new RuntimeException("Evento lotado");
        }

        inscricaoExistente.setDataInscricao(inscricaoAtualizada.getDataInscricao());
        inscricaoExistente.setParticipante(participante);
        inscricaoExistente.setEvento(evento);

        return inscricaoExistente;
    }

    public void deletarInscricao(Long id) {
        Inscricao inscricao = buscarInscricao(id);
        inscricao.setDeleted(true);
    }
}