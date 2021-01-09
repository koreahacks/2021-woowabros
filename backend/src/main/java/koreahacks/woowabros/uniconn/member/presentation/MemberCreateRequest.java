package koreahacks.woowabros.uniconn.member.presentation;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import koreahacks.woowabros.uniconn.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberCreateRequest {

    @Email
    private String email;

    @NotBlank
    @Length(min = 8, max = 16)
    private String password;

    @NotBlank
    private String major;

    public Member toMember() {
        return Member.builder()
            .email(this.email)
            .password(this.password)
            .major(this.major)
            .isVerified(false)
            .authCode(UUID.randomUUID().toString())
            .univ(this.email)
            .build();
    }
}
