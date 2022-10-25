package br.edu.ifpb.project.quiz.jogo.service;

import java.util.List;

import br.edu.ifpb.project.quiz.jogo.model.Registro;
import br.edu.ifpb.project.quiz.jogo.model.dto.RegistroDTO;

public interface RegistroService {
    void registrarQuestao(RegistroDTO registro);
    List<Registro> consultarRespostas(String email);
}
