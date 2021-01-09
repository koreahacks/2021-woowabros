package koreahacks.woowabros.uniconn.answer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "answers", createIndex = false, useServerConfiguration = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Answer {

    @Id
    private String id;

    private String content;

    private boolean isSelected;

    private String userId;

    private String questionId;

    @CreatedDate
    @Field(type = FieldType.Date)
    private Date createdAt;
}
