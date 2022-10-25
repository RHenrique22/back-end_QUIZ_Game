package br.edu.ifpb.project.quiz.jogo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {
    private String email;
    private Long idPergunta;
    private Long idResposta;
}
