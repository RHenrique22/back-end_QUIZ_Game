package br.edu.ifpb.project.quiz.jogo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestaoDTO {
    private Long idPergunta;
    private String descricaoPergunta;
    private String temaPergunta;
    private Long idResposta;
    private String descricaoResposta;
    private String temaResposta;
}
