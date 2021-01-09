package koreahacks.woowabros.uniconn.common;

import koreahacks.woowabros.uniconn.member.presentation.dto.AccessToken;

public interface TokenProvider {
    AccessToken createToken(final String subject);

    String getSubject(final String token);
}
