package koreahacks.woowabros.uniconn.question.presentation.dto;

import koreahacks.woowabros.uniconn.question.domain.Question;
import lombok.Data;

@Data
public class QuestionCreateRequest {

    private String title;

    private String content;

    private long price;

    private String category;

    private String userId;

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .price(price)
                .category(category)
                .userId(userId)
                .build();
    }
}
