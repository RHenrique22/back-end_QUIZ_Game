package br.edu.ifpb.project.quiz.jogo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;

public class PerguntaTests {

    Pergunta pergunta;
    
    @BeforeEach
    private void config() {
        this.pergunta = new Pergunta(
            1L
           ,"O QUE É, O QUE É? TEM CAUDA, MAS NÃO É CÃO; NÃO TEM ASAS, MAS SABE VOAR. SE A LARGAM, NÃO SOBE, E SAI AO VENTO A BRINCAR"
           ,"O QUE É, O QUE É"
           ,new Resposta(
                1L
               ,"A PIPA"
               ,"O QUE É, O QUE É"
            )
        );
    }

    @Test
    public void criarPerguntaComBuilder() {
        Assertions.assertEquals(
            this.pergunta
           ,Pergunta.builder()
                    .id(1L)
                    .descricao("O QUE É, O QUE É? TEM CAUDA, MAS NÃO É CÃO; NÃO TEM ASAS, MAS SABE VOAR. SE A LARGAM, NÃO SOBE, E SAI AO VENTO A BRINCAR")
                    .tema("O QUE É, O QUE É")
                    .resposta(this.pergunta.getResposta())
                    .build()
        );
    }

}
