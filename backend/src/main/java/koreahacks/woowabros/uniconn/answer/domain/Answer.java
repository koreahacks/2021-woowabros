package koreahacks.woowabros.uniconn.answer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;

@Document(indexName = "answers", createIndex = false, useServerConfiguration = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Answer {

    @Id
    private String id;

    private String content;

    private String userId;

    private String questionId;

    @CreatedDate
    private Instant createdAt;
}
