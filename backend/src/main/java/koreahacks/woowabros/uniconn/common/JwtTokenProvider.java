package koreahacks.woowabros.uniconn.common;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import koreahacks.woowabros.uniconn.member.presentation.dto.AccessToken;

@Component
public class JwtTokenProvider implements TokenProvider {
    private final String secretKey;
    private final long validityInMilliseconds;

    public JwtTokenProvider(@Value("${secrets.jwt.token.secret-key}") final String secretKey,
        @Value("${secrets.jwt.token.expire-length}") final long validityInMilliseconds) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public AccessToken createToken(final String subject) {
        final Claims claims = Jwts.claims().setSubject(subject);

        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return new AccessToken(Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact());
    }

    public String getSubject(final String token) {
        return validateToken(token).getBody().getSubject();
    }

    private Jws<Claims> validateToken(final String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }
}
