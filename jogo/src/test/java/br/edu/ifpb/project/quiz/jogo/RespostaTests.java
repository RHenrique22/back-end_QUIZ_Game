package br.edu.ifpb.project.quiz.jogo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpb.project.quiz.jogo.model.Resposta;

public class RespostaTests {

    Resposta resposta;

    @BeforeEach
    private void config() {
        this.resposta = new Resposta(
            1L
           ,"A PIPA"
           ,"O QUE É, O QUE É"
        );
    }

    @Test
    public void criarRespostaComBuilder() {
        Assertions.assertEquals(
            this.resposta
           ,Resposta.builder()
                    .id(1L)
                    .descricao("A PIPA")
                    .tema("O QUE É, O QUE É")
                    .build()
        );
    }
}
