package koreahacks.woowabros.uniconn.answer.presentation.dto;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import lombok.Data;

@Data
public class AnswerCreateRequest {

    private String content;

    private String questionId;

    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .questionId(questionId)
                .build();
    }
}
