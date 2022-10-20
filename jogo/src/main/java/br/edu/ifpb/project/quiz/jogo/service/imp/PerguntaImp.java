package br.edu.ifpb.project.quiz.jogo.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.model.dto.ResponseDTO;
import br.edu.ifpb.project.quiz.jogo.repository.PerguntaRepository;
import br.edu.ifpb.project.quiz.jogo.service.PerguntaService;

@Service
public class PerguntaImp implements PerguntaService {

    @Autowired
    PerguntaRepository perguntaRepository;

    @Override
    public Pergunta createPergunta(Pergunta pergunta) {
        return this.perguntaRepository.save(
            Pergunta.builder()
                    .descricao(pergunta.getDescricao())
                    .tema(pergunta.getTema())
                    .resposta(pergunta.getResposta())
                    .build()
        );
    }

    @Override
    public ResponseDTO resultPergunta(Pergunta pergunta, Resposta resposta) {
        return pergunta.getResposta().equals(resposta) ? new ResponseDTO("ACERTOU") :  new ResponseDTO("ERROU");
    }

    @Override
    public Pergunta randomPergunta(String tema) {
        Optional<List<Pergunta>> perguntas = perguntaRepository.findByTema(tema);
        Random random = new Random();

        if(perguntas.isPresent()) {
            return perguntas.get().get(random.nextInt(0, perguntas.get().size()));
        }

        return null;
    }

    @Override
    public List<Pergunta> getPerguntas() {
        return this.perguntaRepository.findAll();
    }
    
}