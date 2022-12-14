package br.edu.ifpb.project.quiz.jogo.service.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.repository.PerguntaRepository;
import br.edu.ifpb.project.quiz.jogo.repository.RespostaRepository;
import br.edu.ifpb.project.quiz.jogo.service.RespostaService;

@Service
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
    public List<Resposta> randomResposta(String tema, Pergunta pergunta) {
        Optional<Pergunta> perguntaOpt = this.perguntaRepository.findById(pergunta.getId());
        Optional<List<Resposta>> respostas = this.respostaRepository.findByTema(tema);
        Set<Resposta> respostasRandom = new HashSet<>();
        Random random = new Random();

        if(respostas.isPresent() && respostas.get().size() >= 4) {

            if(perguntaOpt.isPresent()) {
                respostasRandom.add(perguntaOpt.get().getResposta());
            }
            else {
                return null;
            }

            while(respostasRandom.size() < 4) {
                respostasRandom.add(respostas.get().get(random.nextInt(0, respostas.get().size())));
            }

            List<Resposta> respostasReturn = new ArrayList<>(respostasRandom);
            Collections.shuffle(respostasReturn);

            return respostasReturn;
        }

        return null;
    }

}
