package br.edu.ifpb.project.quiz.jogo.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;

@Service
public interface PerguntaService {
    Pergunta createPergunta(Pergunta pergunta);
    Pergunta randomPergunta(String tema);
    boolean resultPergunta(Pergunta pergunta, Resposta resposta);
}
