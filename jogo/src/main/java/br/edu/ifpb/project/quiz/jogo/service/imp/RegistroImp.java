package br.edu.ifpb.project.quiz.jogo.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Registro;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.model.dto.RegistroDTO;
import br.edu.ifpb.project.quiz.jogo.repository.PerguntaRepository;
import br.edu.ifpb.project.quiz.jogo.repository.RegistroRepository;
import br.edu.ifpb.project.quiz.jogo.repository.RespostaRepository;
import br.edu.ifpb.project.quiz.jogo.service.PerguntaService;
import br.edu.ifpb.project.quiz.jogo.service.RegistroService;

@Service
public class RegistroImp implements RegistroService {

    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    RespostaRepository respostaRepository;

    @Autowired
    PerguntaService perguntaService;

    @Override
    public void registrarQuestao(RegistroDTO registro) {
        Optional<Pergunta> perguntaOpt = this.perguntaRepository.findById(registro.getIdPergunta());
        Optional<Resposta> respostaOpt = this.respostaRepository.findById(registro.getIdResposta());

        if(perguntaOpt.isPresent() && respostaOpt.isPresent()) {
            this.registroRepository.save(
                Registro.builder()
                        .email(registro.getEmail())
                        .resultado(this.perguntaService.resultPergunta(perguntaOpt.get(), respostaOpt.get()))
                        .build()
            );
        }
    }

    @Override
    public List<Registro> consultarRespostas(String email) {
        Optional<List<Registro>> registroOpt = this.registroRepository.consultarRespostas(email);

        if(registroOpt.isPresent()) {
            return registroOpt.get();
        }
        else {
            return null;
        }

    }
}
