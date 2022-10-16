package br.edu.ifpb.project.quiz.jogo.service;

import java.util.List;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;

public interface RespostaService {
    Resposta createResposta(Resposta resposta);
    List<Resposta> randomResposta(String tema, Pergunta pergunta);
}
