package br.edu.ifpb.project.quiz.jogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.model.dto.QuestaoDTO;
import br.edu.ifpb.project.quiz.jogo.service.imp.PerguntaImp;
import br.edu.ifpb.project.quiz.jogo.service.imp.RespostaImp;

@RestController
@RequestMapping(value = "api/jogo")
public class JogoController {
    
    @Autowired
    PerguntaImp perguntaService;

    @Autowired
    RespostaImp respostaService;

    @GetMapping(value = "/respostaUser")
    public Boolean verificarRespostaUser() {
        return true;
    }

    @GetMapping(value = "/perguntas")
    public List<Pergunta> getPerguntas() {
        return this.perguntaService.getPerguntas();
    }

    @GetMapping(value = "/questao")
    public QuestaoDTO questao() {
        Pergunta pergunta = this.perguntaService.randomPergunta("O QUE É, O QUE É");
        List<Resposta> respostas = this.respostaService.randomResposta("O QUE É, O QUE É", pergunta);

        QuestaoDTO questao = new QuestaoDTO(pergunta, respostas);

        return questao;
    }

}
