package koreahacks.woowabros.uniconn.question.presentation.dto;

import koreahacks.woowabros.uniconn.question.domain.Question;
import lombok.Data;

@Data
public class QuestionCreateRequest {

    private String title;

    private String content;

    private boolean isPayment;

    private String category;

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .price(0L)
                .category(category)
                .build();
    }
}
