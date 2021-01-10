package koreahacks.woowabros.uniconn.question.presentation.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import koreahacks.woowabros.uniconn.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class QuestionResponse {
    private String id;

    private String title;

    private String content;

    private long price;

    private String category;

    private String userId;

    private Date createdAt;

    public static List<QuestionResponse> listOf(List<Question> questions) {
        return questions.stream()
            .map(QuestionResponse::of)
            .collect(Collectors.toList());
    }

    private static QuestionResponse of(Question question) {
        return QuestionResponse.builder()
            .id(question.getId())
            .title(question.getTitle())
            .content(question.getContent())
            .price(question.getPrice())
            .category(question.getCategory())
            .userId(question.getUserId())
            .createdAt(question.getCreatedAt())
            .build();
    }
}
