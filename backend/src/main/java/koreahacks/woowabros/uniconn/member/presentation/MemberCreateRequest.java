package koreahacks.woowabros.uniconn.member.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberCreateRequest {
    private String email;
    private String password;
    private String major;
}
