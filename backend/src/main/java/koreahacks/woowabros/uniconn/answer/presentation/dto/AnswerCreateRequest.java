package koreahacks.woowabros.uniconn.answer.presentation.dto;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.member.domain.Major;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AnswerCreateRequest {

    @NotBlank
    private String content;

    @NotNull
    private Major major;

    private String questionId;

    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .major(major)
                .questionId(questionId)
                .build();
    }
}
