package com.nextloop.nearlog.api.domain.auth;

import com.nextloop.nearlog.api.domain.user.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int expiryDates;

    public String generateToken(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        return createToken(principal);
    }

    public String createToken(UserPrincipal principal) {

        return Jwts.builder()
                .setSubject(Long.toString(principal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(getExpiryTime())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Date getExpiryTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, expiryDates);
        return calendar.getTime();
    }
}
