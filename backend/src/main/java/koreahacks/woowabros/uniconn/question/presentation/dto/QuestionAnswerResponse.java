package koreahacks.woowabros.uniconn.question.presentation.dto;

import static java.util.stream.Collectors.*;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import koreahacks.woowabros.uniconn.answer.domain.Answer;
import koreahacks.woowabros.uniconn.answer.presentation.dto.AnswerResponse;
import koreahacks.woowabros.uniconn.question.domain.Question;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionAnswerResponse {

    private String id;

    private String title;

    private String content;

    private long price;

    @JsonProperty("selected")
    private String selectedCommentId;

    private Date createdAt;

    private List<AnswerResponse> answers;

    public static QuestionAnswerResponse of(Question question, List<Answer> answers) {
        return QuestionAnswerResponse.builder()
            .id(question.getId())
            .title(question.getTitle())
            .content(question.getContent())
            .price(question.getPrice())
            .createdAt(question.getCreatedAt())
            .selectedCommentId(answers.stream()
                .filter(Answer::isSelected)
                .findFirst()
                .orElse(Answer.builder().build())
                .getId())
            .answers(answers.stream().map(AnswerResponse::of).collect(toList()))
            .build();
    }
}
