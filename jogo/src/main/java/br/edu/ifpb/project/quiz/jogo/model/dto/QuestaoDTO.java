package br.edu.ifpb.project.quiz.jogo.model.dto;

import java.util.List;

import br.edu.ifpb.project.quiz.jogo.model.Pergunta;
import br.edu.ifpb.project.quiz.jogo.model.Resposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestaoDTO {
    private Pergunta pergunta;
    private List<Resposta> respostas;
}
