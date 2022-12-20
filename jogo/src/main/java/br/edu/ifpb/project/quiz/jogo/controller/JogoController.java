package br.edu.ifpb.project.quiz.jogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.project.quiz.jogo.model.Perfil;
import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Registro;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.model.dto.EmailDTO;
import br.edu.ifpb.project.quiz.jogo.model.dto.PerfilDTO;
import br.edu.ifpb.project.quiz.jogo.model.dto.QuestaoDTO;
import br.edu.ifpb.project.quiz.jogo.model.dto.RegistroDTO;
import br.edu.ifpb.project.quiz.jogo.model.dto.RequestDTO;
import br.edu.ifpb.project.quiz.jogo.model.dto.UserDTO;
import br.edu.ifpb.project.quiz.jogo.rabbitmq.ProducerRabbitMQ;
import br.edu.ifpb.project.quiz.jogo.service.PerfilService;
import br.edu.ifpb.project.quiz.jogo.service.PerguntaService;
import br.edu.ifpb.project.quiz.jogo.service.RegistroService;
import br.edu.ifpb.project.quiz.jogo.service.RespostaService;

@RestController
@RequestMapping(value = "api/jogo")
public class JogoController {

    private ProducerRabbitMQ producer = new ProducerRabbitMQ();
    
    @Autowired
    PerguntaService perguntaService;

    @Autowired
    RespostaService respostaService;

    @Autowired
    RegistroService registroService;

    @Autowired
    PerfilService perfilService;

    @PostMapping(value = "/respostaUser")
    public void verificarRespostaUser(@RequestBody RequestDTO response) {
        this.producer.publishInQueue(response);
    }

    @GetMapping(value = "/perguntas")
    public List<Pergunta> getPerguntas() {
        return this.perguntaService.getPerguntas();
    }

    @GetMapping(value = "/questao")
    public QuestaoDTO questao() {
        Pergunta pergunta = this.perguntaService.randomPergunta("O QUE É, O QUE É");
        List<Resposta> respostas = this.respostaService.randomResposta("O QUE É, O QUE É", pergunta);

        return new QuestaoDTO(pergunta, respostas);
    }

    @PostMapping(value = "/registroQuestao")
    public void registarQuestao(@RequestBody RegistroDTO registro) {
        this.registroService.registrarQuestao(registro);
    }

    @PostMapping(value = "/consultarRespostas")
    public List<Registro> consultarResposta(@RequestBody UserDTO user) {
        return this.registroService.consultarRespostas(user.getEmail());
    }

    @PostMapping(value = "/criarPerfil")
    public Perfil criarPerfil(@RequestBody PerfilDTO perfil) {
        return this.perfilService.createPerfil(perfil);
    }

    @PostMapping(value = "/perfilExist")
    public boolean perfilExist(@RequestBody EmailDTO email) {
        return this.perfilService.perfilExist(email.getEmail());
    }

    @PostMapping(value = "/buscarPerfil")
    public List<Perfil> buscarPerfil(@RequestBody EmailDTO email) {
        return this.perfilService.findByEmail(email.getEmail());
    }

    @PutMapping(value = "/editarPerfil")
    public Perfil editarPerfil(@RequestBody PerfilDTO perfil) {
        return this.perfilService.updatePerfil(perfil);
    }

}