package br.edu.ifpb.project.quiz.jogo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpb.project.quiz.jogo.model.Resposta;

@Service
public interface RespostaService {
    Resposta createResposta(Resposta resposta);
    List<Resposta> randomResposta(String tema, Long idPergunta);
}
