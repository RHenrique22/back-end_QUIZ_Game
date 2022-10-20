package br.edu.ifpb.project.quiz.jogo.model.dto;

import java.io.Serializable;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO implements Serializable {
    private Pergunta pergunta;
    private Resposta resposta;
    private String   username;
}
