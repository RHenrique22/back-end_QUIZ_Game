package br.edu.ifpb.project.quiz.jogo.service;

import java.util.List;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.model.dto.ResponseDTO;

public interface PerguntaService {
    Pergunta createPergunta(Pergunta pergunta);
    Pergunta randomPergunta(String tema);
    ResponseDTO resultPergunta(Pergunta pergunta, Resposta resposta);
    List<Pergunta> getPerguntas();
}
