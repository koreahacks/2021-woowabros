package koreahacks.woowabros.uniconn.member.presentation;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import koreahacks.woowabros.uniconn.common.AuthCodeGenerator;
import koreahacks.woowabros.uniconn.member.domain.Member;
import koreahacks.woowabros.uniconn.member.domain.University;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberCreateRequest {

    @Email
    private String email;

    @Length(min = 8, max = 16)
    private String password;

    @Length(min = 1, max = 20)
    private String nickname;

    @NotBlank
    private String major;

    public Member toMember(AuthCodeGenerator generator) {
        return Member.builder()
            .email(this.email)
            .nickname(this.nickname)
            .password(this.password)
            .major(this.major)
            .isVerified(false)
            .authCode(generator.generate())
            .univ(University.from(this.email).name())
            .build();
    }

}
