package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Dto.Request.RefreshTokenRequestDto;
import com.example.SpringSecurity.Dto.Response.RefreshTokenResponseDto;
import com.example.SpringSecurity.Services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/auth")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PutMapping ("/refresh/token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDto requestDto){


        return ResponseEntity.ok(
                refreshTokenService.refreshToken(requestDto)
        );
    }
}
