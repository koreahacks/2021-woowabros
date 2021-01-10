package koreahacks.woowabros.uniconn.member.presentation.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

import koreahacks.woowabros.uniconn.member.domain.Major;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionAnswerResponse;
import koreahacks.woowabros.uniconn.question.presentation.dto.QuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberInfoResponse {
    @Id
    private String id;

    private String email;

    private String password;

    private boolean isVerified;

    private String univ;

    private Major major;

    private String authCode;

    private List<QuestionResponse> questionResponses;

    public static MemberInfoResponse of(Member member, List<QuestionResponse> questionResponses) {
        return MemberInfoResponse.builder()
            .id(member.getId())
            .email(member.getEmail())
            .password(member.getPassword())
            .isVerified(member.isVerified())
            .univ(member.getUniv())
            .major(member.getMajor())
            .authCode(member.getAuthCode())
            .questionResponses(questionResponses)
            .build();
    }
}
