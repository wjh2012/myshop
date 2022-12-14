package com.ggomg.myshop.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class TokenProvideService {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String create(Long memberId) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("myshop") // 발급자
                .setSubject(String.valueOf(memberId)) // 토큰 제목(주로 이메일)
                .signWith(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();
    }

    public String validateMember(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
