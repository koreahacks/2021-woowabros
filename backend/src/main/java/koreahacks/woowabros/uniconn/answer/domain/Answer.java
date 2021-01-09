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

import java.util.*;

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

    @Field(type = FieldType.Nested, includeInParent = true)
    @Builder.Default
    private List<Reaction> reactions = new ArrayList<>();

    private String userId;

    private String questionId;

    @CreatedDate
    @Field(type = FieldType.Date)
    private Date createdAt;

    public void addReaction(ReactionType type, String userId) {
        Optional<Reaction> existedReaction = reactions.stream()
                .filter(it -> Objects.equals(it.getUserId(), userId))
                .findFirst();

        if (existedReaction.isPresent()) {
            Reaction reaction = existedReaction.get();
            reaction.setType(type);
        } else {
            reactions.add(new Reaction(type, userId));
        }
    }

    public long countLike() {
        return count(ReactionType.LIKE);
    }

    public long countDislike() {
        return count(ReactionType.DISLIKE);
    }

    private long count(ReactionType type) {
        return reactions.stream()
                .filter(it -> it.getType() == type)
                .count();
    }
}
