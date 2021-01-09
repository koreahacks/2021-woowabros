package koreahacks.woowabros.uniconn.member.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(indexName = "members", createIndex = false, useServerConfiguration = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member {

    @Id
    private String id;

    private String email;

    private String password;

    private boolean isVerified;

    private String univ;

    private String major;
}
