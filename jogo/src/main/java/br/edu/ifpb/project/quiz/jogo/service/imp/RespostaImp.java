package br.edu.ifpb.project.quiz.jogo.service.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.repository.PerguntaRepository;
import br.edu.ifpb.project.quiz.jogo.repository.RespostaRepository;
import br.edu.ifpb.project.quiz.jogo.service.RespostaService;

public class RespostaImp implements RespostaService {

    @Autowired
    RespostaRepository respostaRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @Override
    public Resposta createResposta(Resposta resposta) {
        return this.respostaRepository.save(
            Resposta.builder()
                    .descricao(resposta.getDescricao())
                    .tema(resposta.getTema())
                    .build()
        );
    }

    @Override
    public List<Resposta> randomResposta(String tema, Long idPergunta) {
        Optional<Pergunta> pergunta = this.perguntaRepository.findById(idPergunta);
        List<Resposta> respostas = this.respostaRepository.findByTema(tema);
        Set<Resposta> respostasRandom = new HashSet<>();
        Random random = new Random();

        if(!respostas.isEmpty() && respostas.size() >= 4) {

            if(pergunta.isPresent()) {
                respostasRandom.add(pergunta.get().getResposta());
            }
            else {
                return null;
            }

            while(respostasRandom.size() < 4) {
                respostasRandom.add(respostas.get(random.nextInt(0, respostas.size())));
            }

            List<Resposta> respostasReturn = new ArrayList<>(respostasRandom);
            Collections.shuffle(respostasReturn);

            return respostasReturn;
        }

        return null;
    }

}
