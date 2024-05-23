package com.c_comachi.inused.domain.users.service;

import com.c_comachi.inused.domain.users.dto.response.TokenDto;
import com.c_comachi.inused.domain.users.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookieService {

    public static ResponseCookie createRefreshToken(String refresh) {
        return ResponseCookie.from("refreshToken" , refresh)
                .path("/")
                .maxAge(14 * 24 * 60 * 60 * 1000)
                .secure(true)
                .domain("https://www.inused.store")
                .domain("https://inused.store")
                .httpOnly(true)
                .sameSite("none")
                .build();
    }

    public void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        if (tokenDto.getRefreshToken() != null) {
            response.addHeader(TokenProvider.REFRESH_HEADER, tokenDto.getRefreshToken());
            response.addHeader("Set-Cookie", createRefreshToken(tokenDto.getRefreshToken()).toString());
        }

    }

}