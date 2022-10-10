package br.edu.ifpb.project.quiz.jogo.service.imp;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.repository.PerguntaRepository;
import br.edu.ifpb.project.quiz.jogo.service.PerguntaService;

public class PerguntaImp implements PerguntaService {

    @Autowired
    PerguntaRepository perguntaRepository;

    @Override
    public Pergunta createPergunta(Pergunta pergunta) {
        return null;
    }

    @Override
    public boolean resultPergunta(Pergunta pergunta, Resposta resposta) {
        return pergunta.getResposta().equals(resposta);
    }

    @Override
    public Pergunta randomPergunta(String tema) {
        List<Pergunta> perguntas = perguntaRepository.findByTema(tema);
        Random random = new Random();

        if(!perguntas.isEmpty()) {
            return perguntas.get(random.nextInt(0, perguntas.size()));
        }

        return null;
    }
    
}