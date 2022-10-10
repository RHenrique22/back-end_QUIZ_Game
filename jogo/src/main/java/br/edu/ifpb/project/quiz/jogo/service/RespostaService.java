package br.edu.ifpb.project.quiz.jogo.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;

@Service
public interface RespostaService {
    Resposta createResposta(Resposta resposta);
    Set<Resposta> randomResposta(String tema, Long idPergunta);
}
