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

    private boolean isSelected;

    private long likeCount;

    private long dislikeCount;

    private Date createdAt;

    private String createdBy;

    public static AnswerResponse of(Answer answer) {
        return AnswerResponse.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .isSelected(answer.isSelected())
                .likeCount(answer.countLike())
                .dislikeCount(answer.countDislike())
                .createdAt(answer.getCreatedAt())
                .createdBy(answer.getUserId())
                .build();
    }
}
