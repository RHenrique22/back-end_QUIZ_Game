package br.edu.ifpb.project.quiz.jogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.project.quiz.jogo.model.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    
}
