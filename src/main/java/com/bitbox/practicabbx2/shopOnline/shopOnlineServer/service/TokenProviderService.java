package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.auth.TokenDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.token.ITokenProviderService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenProviderService implements ITokenProviderService {

    @Value("${shopOnlineServerBBX.auth.tokenSecret}")
    private String tokenSecret;

    @Value("${shopOnlineServerBBX.auth.tokenExpirationMsec}")
    private Long tokenExpirationMsec;

    @Value("${shopOnlineServerBBX.auth.refreshTokenExpirationMsec}")
    private Long refreshTokenExpirationMsec;

    @Override
    public TokenDTO generateAccessToken(String subject) {
        Date now = new Date();
        Long duration = now.getTime() + tokenExpirationMsec;
        Date expiryDate = new Date(duration);
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
        return new TokenDTO(TokenDTO.TokenType.ACCESS, token, duration, LocalDateTime.ofInstant(expiryDate.toInstant(),
                ZoneId.systemDefault()));
    }

    @Override
    public TokenDTO generateRefreshToken(String subject) {
        Date now = new Date();
        Long duration = now.getTime() + refreshTokenExpirationMsec;
        Date expiryDate = new Date(duration);
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
        return new TokenDTO(TokenDTO.TokenType.REFRESH, token, duration, LocalDateTime.ofInstant(expiryDate.toInstant(),
                ZoneId.systemDefault()));
    }

    @Override
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    public LocalDateTime getExpiryDateFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parse(token);
            return true;
        } catch (SignatureException ex) {
            ex.printStackTrace();
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
        } catch (ExpiredJwtException ex) {
            ex.printStackTrace();
        } catch (UnsupportedJwtException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
