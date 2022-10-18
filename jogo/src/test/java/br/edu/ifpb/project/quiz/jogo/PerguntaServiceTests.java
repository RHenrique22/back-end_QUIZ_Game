package br.edu.ifpb.project.quiz.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.repository.PerguntaRepository;
import br.edu.ifpb.project.quiz.jogo.service.imp.PerguntaImp;

@ExtendWith(MockitoExtension.class)
public class PerguntaServiceTests {
    
    @InjectMocks
    PerguntaImp perguntaService;

    @Mock
    PerguntaRepository perguntaRepository;

    @Test
    public void respostaParaPerguntaEstaCorreta() {
        Pergunta pergunta = new Pergunta(
            1L
           ,"O QUE É, O QUE É? TEM CAUDA, MAS NÃO É CÃO; NÃO TEM ASAS, MAS SABE VOAR. SE A LARGAM, NÃO SOBE, E SAI AO VENTO A BRINCAR"
           ,"O QUE É, O QUE É"
           ,new Resposta(
                1L
               ,"A PIPA"
               ,"O QUE É, O QUE É"
            )
        );

        Resposta resposta = new Resposta(
            1L
           ,"A PIPA"
           ,"O QUE É, O QUE É"
        );

        Assertions.assertEquals(true, this.perguntaService.resultPergunta(pergunta, resposta));
    }


    @Test
    public void respostaParaPerguntaEstaIncorreta() {
        Pergunta pergunta = new Pergunta(
            1L
           ,"O QUE É, O QUE É? TEM CAUDA, MAS NÃO É CÃO; NÃO TEM ASAS, MAS SABE VOAR. SE A LARGAM, NÃO SOBE, E SAI AO VENTO A BRINCAR"
           ,"O QUE É, O QUE É"
           ,new Resposta(
                1L
               ,"A PIPA"
               ,"O QUE É, O QUE É"
            )
        );

        Resposta resposta = new Resposta(
            2L
           ,"UM BOTÃO"
           ,"O QUE É, O QUE É"
        );

        Assertions.assertNotEquals(true, this.perguntaService.resultPergunta(pergunta, resposta));
    }

    @Test
    public void randomizarPerguntaComTemaExistente() {

        List<Pergunta> perguntasTema = new ArrayList<Pergunta>();

        perguntasTema.add(
            new Pergunta(
                1L
               ,"O QUE É, O QUE É? TEM CAUDA, MAS NÃO É CÃO; NÃO TEM ASAS, MAS SABE VOAR. SE A LARGAM, NÃO SOBE, E SAI AO VENTO A BRINCAR"
               ,"O QUE É, O QUE É"
               ,new Resposta(
                    1L
                   ,"A PIPA"
                   ,"O QUE É, O QUE É"
                )
            )
        );

        perguntasTema.add(
            new Pergunta(
                2L
               ,"O QUE É, O QUE É? TEM MAIS DE DEZ CABEÇAS E NÃO SABE PENSAR"
               ,"O QUE É, O QUE É"
               ,new Resposta(
                    2L
                   ,"UMA CAIXA DE FÓSFOROS"
                   ,"O QUE É, O QUE É"
                )
            )
        );

        Mockito.doReturn(perguntasTema).when(this.perguntaRepository).findByTema("O QUE É, O QUE É");

        Assertions.assertNotNull(this.perguntaService.randomPergunta("O QUE É, O QUE É"));
    }

    @Test
    public void randomizarPerguntaComTemaInexistente() {

        List<Pergunta> perguntasTema = new ArrayList<Pergunta>();

        perguntasTema.add(
            new Pergunta(
                1L
               ,"O QUE É, O QUE É? TEM CAUDA, MAS NÃO É CÃO; NÃO TEM ASAS, MAS SABE VOAR. SE A LARGAM, NÃO SOBE, E SAI AO VENTO A BRINCAR"
               ,"O QUE É, O QUE É"
               ,new Resposta(
                    1L
                   ,"A PIPA"
                   ,"O QUE É, O QUE É"
                )
            )
        );

        perguntasTema.add(
            new Pergunta(
                2L
               ,"O QUE É, O QUE É? TEM MAIS DE DEZ CABEÇAS E NÃO SABE PENSAR"
               ,"O QUE É, O QUE É"
               ,new Resposta(
                    2L
                   ,"UMA CAIXA DE FÓSFOROS"
                   ,"O QUE É, O QUE É"
                )
            )
        );

        Mockito.doReturn(Optional.empty()).when(this.perguntaRepository).findByTema("O QUE É, O QUE É");

        Assertions.assertNull(this.perguntaService.randomPergunta("O QUE É, O QUE É"));
    }

}
