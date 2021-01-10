package koreahacks.woowabros.uniconn.question.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(indexName = "questions", createIndex = false, useServerConfiguration = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Question {

    @Id
    private String id;

    private String title;

    private String content;

    private long price;

    private String category;

    private String userId;

    @CreatedDate
    @Field(type = FieldType.Date)
    private Date createdAt;

    public void verifyMember(String userId) {
        if (!this.userId.equals(userId)) {
            throw new IllegalArgumentException("Question에 해당하는 userId가 아닙니다.");
        }
    }
}
