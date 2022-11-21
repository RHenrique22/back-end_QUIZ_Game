package br.edu.ifpb.project.quiz.jogo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {
    private Long   id;

    private String apelido;

    private String bio;

    private String user;
}
