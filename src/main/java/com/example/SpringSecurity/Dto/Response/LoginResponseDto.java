package com.example.SpringSecurity.Dto.Response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
}
