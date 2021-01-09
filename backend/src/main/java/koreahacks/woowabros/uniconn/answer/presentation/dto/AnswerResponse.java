package koreahacks.woowabros.uniconn.answer.presentation.dto;

import koreahacks.woowabros.uniconn.answer.domain.Answer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AnswerResponse {

    private String id;

    private String content;

    private Date createdAt;

    private String createdBy;

    public static AnswerResponse of(Answer answer) {
        return AnswerResponse.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt())
                .createdBy(answer.getUserId())
                .build();
    }
}
