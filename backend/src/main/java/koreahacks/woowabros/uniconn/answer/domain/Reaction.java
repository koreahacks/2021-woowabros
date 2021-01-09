package koreahacks.woowabros.uniconn.answer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reaction {

    private ReactionType type;

    private String userId;
}
