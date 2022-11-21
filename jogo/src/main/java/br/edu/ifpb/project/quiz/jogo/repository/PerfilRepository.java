package br.edu.ifpb.project.quiz.jogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.project.quiz.jogo.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<List<Perfil>> findByEmail(String email);

}
