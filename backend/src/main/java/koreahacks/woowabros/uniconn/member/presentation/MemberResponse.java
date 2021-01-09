package koreahacks.woowabros.uniconn.member.presentation;

import koreahacks.woowabros.uniconn.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberResponse {
    private String id;

    private String email;

    private String nickname;

    private String univ;

    private String major;

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
            .id(member.getId())
            .email(member.getEmail())
            .nickname(member.getNickname())
            .univ(member.getUniv())
            .major(member.getMajor())
            .build();
    }
}
