package koreahacks.woowabros.uniconn.question.presentation.dto;

import koreahacks.woowabros.uniconn.question.domain.Question;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class QuestionWithSelectedResponse {

    private String id;

    private String title;

    private String content;

    private long price;

    private boolean isSelected;

    private Date createdAt;

    public static QuestionWithSelectedResponse from(Question question, boolean isSelected) {
        return QuestionWithSelectedResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .price(question.getPrice())
                .isSelected(isSelected)
                .createdAt(question.getCreatedAt())
                .build();
    }
}
