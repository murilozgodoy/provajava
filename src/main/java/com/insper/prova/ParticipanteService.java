package com.insper.prova;

import org.springframework.stereotype.Service;



import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

@Service
public class ParticipanteService {

    private Map<Long, Participante> participantes = new HashMap<>();
    private Long proximoId = 1L;

    public Participante cadastrarParticipante(Participante participante) {
        if (participante == null) {
            throw new RuntimeException("Participante não pode ser nulo");
        }

        if (participante.getNome() == null || participante.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        if (participante.getEmail() == null || participante.getEmail().isBlank()) {
            throw new RuntimeException("Email é obrigatório");
        }

        if (participante.getCpf() == null || participante.getCpf().isBlank()) {
            throw new RuntimeException("CPF é obrigatório");
        }

        if (participante.getDataNascimento() == null) {
            throw new RuntimeException("Data de nascimento é obrigatória");
        }

        for (Participante p : participantes.values()) {
            if (!p.isDeleted() && p.getCpf().equals(participante.getCpf())) {
                throw new RuntimeException("CPF já cadastrado");
            }

            if (!p.isDeleted() && p.getEmail().equals(participante.getEmail())) {
                throw new RuntimeException("Email já cadastrado");
            }
        }

        participante.setId(proximoId);
        proximoId++;

        participantes.put(participante.getId(), participante);
        return participante;
    }

    //aqui nao seriai so listar todos, mas sim somenre os ativos
    public Collection<Participante> listarParticipantes() {
        Collection<Participante> ativos = new ArrayList<>();

        for (Participante participante : participantes.values()) {
            if (!participante.isDeleted()) {
                ativos.add(participante);
            }
        }

        return ativos;
    }

    //msm coisa nesse, somente buscar ativos
    public Participante buscarParticipante(Long id) {
        Participante participante = participantes.get(id);

        if (participante == null || participante.isDeleted()) {
            throw new RuntimeException("Participante não encontrado");
        }

        return participante;
    }

    public Participante atualizarParticipante(Long id, Participante participanteAtualizado) {
        Participante participanteExistente = buscarParticipante(id);

        if (participanteAtualizado == null) {
            throw new RuntimeException("Participante não pode ser nulo");
        }

        if (participanteAtualizado.getNome() == null || participanteAtualizado.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        if (participanteAtualizado.getEmail() == null || participanteAtualizado.getEmail().isBlank()) {
            throw new RuntimeException("Email é obrigatório");
        }

        if (participanteAtualizado.getCpf() == null || participanteAtualizado.getCpf().isBlank()) {
            throw new RuntimeException("CPF é obrigatório");
        }

        if (participanteAtualizado.getDataNascimento() == null) {
            throw new RuntimeException("Data de nascimento é obrigatória");
        }

        for (Participante p : participantes.values()) {
            if (!p.isDeleted() && !p.getId().equals(id) && p.getCpf().equals(participanteAtualizado.getCpf())) {
                throw new RuntimeException("CPF já cadastrado");
            }

            if (!p.isDeleted() && !p.getId().equals(id) && p.getEmail().equals(participanteAtualizado.getEmail())) {
                throw new RuntimeException("Email já cadastrado");
            }
        }

        participanteExistente.setNome(participanteAtualizado.getNome());
        participanteExistente.setEmail(participanteAtualizado.getEmail());
        participanteExistente.setCpf(participanteAtualizado.getCpf());
        participanteExistente.setDataNascimento(participanteAtualizado.getDataNascimento());

        return participanteExistente;
    }


    public void deletarParticipante(Long id) {
        Participante participante = buscarParticipante(id);
        participante.setDeleted(true);
    }
}