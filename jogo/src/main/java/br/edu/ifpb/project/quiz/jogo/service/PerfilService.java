package br.edu.ifpb.project.quiz.jogo.service;

import java.util.List;

import br.edu.ifpb.project.quiz.jogo.model.Perfil;
import br.edu.ifpb.project.quiz.jogo.model.dto.PerfilDTO;

public interface PerfilService {
    Perfil  createPerfil(PerfilDTO perfil);
    Perfil  updatePerfil(PerfilDTO perfil);
    boolean perfilExist(String email);
    List<Perfil> findByEmail(String email);
}
