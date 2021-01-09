package koreahacks.woowabros.uniconn.member.domain;

import koreahacks.woowabros.uniconn.exception.AlreadyVerifiedException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "members", createIndex = false, useServerConfiguration = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member {

    @Id
    private String id;

    private String email;

    private String nickname;

    private String password;

    private boolean isVerified;

    private String univ;

    private Major major;

    private String authCode;

    public void verify() {
        if (isVerified) {
            throw new AlreadyVerifiedException();
        }
        this.isVerified = true;
    }

    public boolean isPasswordMatch(String password) {
        return this.password.equals(password);
    }
}
