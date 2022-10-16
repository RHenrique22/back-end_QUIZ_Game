package br.edu.ifpb.project.quiz.jogo.service;

import java.util.List;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;

public interface PerguntaService {
    Pergunta createPergunta(Pergunta pergunta);
    Pergunta randomPergunta(String tema);
    boolean resultPergunta(Pergunta pergunta, Resposta resposta);
    List<Pergunta> getPerguntas();
}
