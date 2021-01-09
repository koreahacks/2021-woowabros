package koreahacks.woowabros.uniconn.question.presentation.dto;

import koreahacks.woowabros.uniconn.question.domain.Question;
import lombok.Data;

@Data
public class QuestionCreateRequest {

    private String title;

    private String content;

    private long price;

    private String category;

    public Question toEntity(String userId) {
        return Question.builder()
                .title(title)
                .content(content)
                .price(price)
                .category(category)
                .userId(userId)
                .build();
    }
}
