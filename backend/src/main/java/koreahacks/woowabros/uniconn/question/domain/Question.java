package koreahacks.woowabros.uniconn.question.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;

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

    private boolean isPayment;

    private String category;

    @CreatedDate
    private Instant createdAt;
}
