package br.edu.ifpb.project.quiz.jogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.model.dto.QuestaoDTO;
import br.edu.ifpb.project.quiz.jogo.service.PerguntaService;
import br.edu.ifpb.project.quiz.jogo.service.RespostaService;

@Controller
@RequestMapping(value = "api/jogo")
public class JogoController {
    
    @Autowired
    PerguntaService perguntaService;

    @Autowired
    RespostaService respostaService;

    @PostMapping(value = "/registrarQuestao")
    public Boolean registrarQuestao(@RequestBody QuestaoDTO questao) {
        Resposta newResposta = new Resposta();
        Pergunta newPergunta = new Pergunta();

        newResposta.builder()
                   .descricao(questao.getDescricaoResposta())
                   .tema(questao.getTemaResposta())
                   .build();

        newResposta = this.respostaService.createResposta(newResposta);

        newPergunta.builder()
                   .descricao(questao.getDescricaoPergunta())
                   .tema(questao.getTemaPergunta())
                   .resposta(newResposta)
                   .build();
        
        this.perguntaService.createPergunta(newPergunta);

        return true;
    }

    @PostMapping(value = "/respostaUser")
    public Boolean verificarRespostaUser(@RequestParam(value = "user") String username) {
        return null;
    }

}
