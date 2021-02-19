package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.token;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.auth.TokenDTO;

import java.time.LocalDateTime;

public interface ITokenProviderService {
    TokenDTO generateAccessToken(String subject);

    TokenDTO generateRefreshToken(String subject);

    String getUsernameFromToken(String token);

    LocalDateTime getExpiryDateFromToken(String token);

    boolean validateToken(String token);
}
