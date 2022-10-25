package br.edu.ifpb.project.quiz.jogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.project.quiz.jogo.model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    @Query("FROM Registro R WHERE R.email = :email")
    Optional<List<Registro>> consultarRespostas(@Param("email") String email);
}
