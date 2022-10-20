package br.edu.ifpb.project.quiz.jogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.model.dto.QuestaoDTO;
import br.edu.ifpb.project.quiz.jogo.model.dto.ResponseDTO;
import br.edu.ifpb.project.quiz.jogo.rabbitmq.RabbitMQ;
import br.edu.ifpb.project.quiz.jogo.service.imp.PerguntaImp;
import br.edu.ifpb.project.quiz.jogo.service.imp.RespostaImp;

@RestController
@RequestMapping(value = "api/jogo")
public class JogoController {

    private RabbitMQ rabbitMQ = new RabbitMQ();
    
    @Autowired
    PerguntaImp perguntaService;

    @Autowired
    RespostaImp respostaService;

    @PostMapping(value = "/respostaUser")
    public void verificarRespostaUser() {
        ResponseDTO response = this.rabbitMQ.consumerInQueue();
        this.rabbitMQ.publishInQueue(response.getUsername(), this.perguntaService.resultPergunta(response.getPergunta(), response.getResposta()));
    }

    @GetMapping(value = "/perguntas")
    public List<Pergunta> getPerguntas() {
        return this.perguntaService.getPerguntas();
    }

    @GetMapping(value = "/questao")
    public QuestaoDTO questao() {
        Pergunta pergunta = this.perguntaService.randomPergunta("O QUE E, O QUE E");
        List<Resposta> respostas = this.respostaService.randomResposta("O QUE E, O QUE E", pergunta);

        return new QuestaoDTO(pergunta, respostas);
    }

}
