package koreahacks.woowabros.uniconn.member.presentation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequest {

    @Email
    private String email;

    @NotBlank
    private String password;
}
