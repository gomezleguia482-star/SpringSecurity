package com.example.SpringSecurity.Services;

import com.example.SpringSecurity.Dto.Request.RefreshTokenRequestDto;
import com.example.SpringSecurity.Dto.Response.RefreshTokenResponseDto;
import com.example.SpringSecurity.Entity.RefreshTokenEntity;
import com.example.SpringSecurity.Entity.UserEntity;
import com.example.SpringSecurity.Jwt.JwtAccessToken;
import com.example.SpringSecurity.Jwt.JwtRefreshToken;
import com.example.SpringSecurity.Repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtAccessToken jwtAccessToken;
    private final JwtRefreshToken jwtRefreshToken;

    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto requestDto) {

        try {
            RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByRefreshToken(
                            requestDto.getRefreshToken())
                    .orElseThrow(() -> new RuntimeException("Refresh token invalid")
                    );

            UserEntity user = refreshTokenEntity.getUserEntity();

            String accessToken = jwtAccessToken.generationAccessToken(
                    user.getEmail(), user.getRole()
            );

            String refreshToken = jwtRefreshToken.generationAccessToken(
                    user.getEmail(), user.getRole()
            );

            refreshTokenEntity.setRefreshToken(refreshToken);

            refreshTokenRepository.save(refreshTokenEntity);

            return RefreshTokenResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
