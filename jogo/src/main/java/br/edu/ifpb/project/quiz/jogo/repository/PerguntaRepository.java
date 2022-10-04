package br.edu.ifpb.project.quiz.jogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    
}
