package br.edu.ifpb.project.quiz.jogo.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.project.quiz.jogo.model.Perfil;
import br.edu.ifpb.project.quiz.jogo.model.dto.PerfilDTO;
import br.edu.ifpb.project.quiz.jogo.repository.PerfilRepository;
import br.edu.ifpb.project.quiz.jogo.service.PerfilService;

@Service
public class PerfilImp implements PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    @Override
    public Perfil createPerfil(PerfilDTO perfil) {
        return this.perfilRepository.save(
            Perfil.builder()
                  .apelido(perfil.getApelido())
                  .bio(perfil.getBio())
                  .email(perfil.getUser())
                  .build()
        );
    }

    @Override
    public Perfil updatePerfil(PerfilDTO perfil) {
        Optional<Perfil> perfilOpt = this.perfilRepository.findById(perfil.getId());

        if(perfilOpt.isPresent()) {
            Perfil perfilObj = perfilOpt.get();

            return this.perfilRepository.save(
                Perfil.builder()
                      .id(perfilObj.getId())
                      .apelido(perfilObj.getApelido())
                      .bio(perfilObj.getBio())
                      .email(perfilObj.getEmail())
                      .build()
            );
        }
        else {
            return null;
        }
    }

    @Override
    public boolean perfilExist(String email) {
        Optional<List<Perfil>> perfilOpt = this.perfilRepository.findByEmail(email);
        return perfilOpt.isPresent();
    }

    @Override
    public List<Perfil> findByEmail(String email) {
        Optional<List<Perfil>> perfilOpt = this.perfilRepository.findByEmail(email);

        if(perfilOpt.isPresent()) {
            return perfilOpt.get();
        }
        else {
            return null;
        }
    }
    
}
