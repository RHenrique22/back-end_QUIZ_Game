package br.edu.ifpb.project.quiz.jogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.project.quiz.jogo.model.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Optional<List<Resposta>> findByTema(String tema);
    
}
