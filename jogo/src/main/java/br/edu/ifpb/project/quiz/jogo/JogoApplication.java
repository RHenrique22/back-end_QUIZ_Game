package br.edu.ifpb.project.quiz.jogo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import br.edu.ifpb.project.quiz.jogo.repository.PerguntaRepository;
import br.edu.ifpb.project.quiz.jogo.repository.RespostaRepository;

@SpringBootApplication
public class JogoApplication implements CommandLineRunner {

	// TODO - IMPLEMENTAÇÃO DE INSERTS DEFAULT PARA A APLICAÇÃO

	@Autowired
	PerguntaRepository perguntaRepository;

	@Autowired
	RespostaRepository respostaRepository;

	public static void main(String[] args) {
		SpringApplication.run(JogoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Resposta> respostas = new ArrayList<>();
		List<Pergunta> perguntas = new ArrayList<>();

		final String TEMA_DEFAULT = "O QUE É, O QUE É";

		/* TODO - RESPOSTAS */

		if(this.respostaRepository.findAll().isEmpty()) {
			respostas.add(
				Resposta.builder()
					    .descricao("A RUA")
					    .tema(TEMA_DEFAULT)
					    .build()
			);

			respostas.add(
				Resposta.builder()
						.descricao("O RELÓGIO")
						.tema(TEMA_DEFAULT)
						.build()
			);

			respostas.add(
				Resposta.builder()
						.descricao("O ALHO")
						.tema(TEMA_DEFAULT)
						.build()	
			);

			respostas.add(
				Resposta.builder()
						.descricao("O TALHER")
						.tema(TEMA_DEFAULT)
						.build()	
			);

			respostas.add(
				Resposta.builder()
						.descricao("O PNEU")
						.tema(TEMA_DEFAULT)
						.build()	
			);

			respostas.add(
				Resposta.builder()
						.descricao("O PASSADO")
						.tema(TEMA_DEFAULT)
						.build()	
			);

			respostas.add(
				Resposta.builder()
						.descricao("O PIOLHO")
						.tema(TEMA_DEFAULT)
						.build()	
			);

			respostas.add(
				Resposta.builder()
						.descricao("O BURACO")
						.tema(TEMA_DEFAULT)
						.build()	
			);

			respostas.add(
				Resposta.builder()
						.descricao("O SAPATO")
						.tema(TEMA_DEFAULT)
						.build()	
			);

			respostas.add(
				Resposta.builder()
						.descricao("A PONTE")
						.tema(TEMA_DEFAULT)
						.build()	
			);

			this.respostaRepository.saveAll(respostas);
		}

		/* TODO - PERGUNTAS */

		if(this.perguntaRepository.findAll().isEmpty()) {
			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? FEITO PARA ANDAR E NÃO ANDA")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(0))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? DÁ MUITAS VOLTAS E NÃO SAI DO LUGAR")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(1))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? TEM CABEÇA E TEM DENTE, NÃO É BICHO E NEM É GENTE")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(2))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? NÃO SE COME, MAS É BOM PARA SE COMER")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(3))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? QUANTO MAIS RUGAS TÊM MAIS NOVO É")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(4))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? NUNCA VOLTA, EMBORA NUNCA TENHA IDO")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(5))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? ANDA COM OS PÉS NA CABEÇA")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(6))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? QUANTO MAIS SE TIRA MAIS SE AUMENTA")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(7))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? FICA CHEIO DURANTE O DIA E VAZIO DURANTE A NOITE")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(8))
					    .build()
			);

			perguntas.add(
				Pergunta.builder()
					    .descricao("O QUE É, O QUE É? MESMO ATRAVESSANDO O RIO NÃO SE MOLHA")
					    .tema(TEMA_DEFAULT)
					    .resposta(respostas.get(9))
					    .build()
			);

			this.perguntaRepository.saveAll(perguntas);
		}
		
	}

}
