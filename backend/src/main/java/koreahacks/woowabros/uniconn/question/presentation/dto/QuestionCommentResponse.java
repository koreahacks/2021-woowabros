package koreahacks.woowabros.uniconn.question.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import koreahacks.woowabros.uniconn.question.domain.Question;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class QuestionCommentResponse {

    private String id;

    private String title;

    private String content;

    private long price;

    @JsonProperty("selected")
    private String selectedCommentId;

    private Date createdAt;

    // TODO: Comment 만들고 추가!
    public static QuestionCommentResponse of(Question question) {
        return QuestionCommentResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .price(question.getPrice())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
