package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Dto.Request.RegisterRequestDto;
import com.example.SpringSecurity.Dto.Response.RegisterResponseDto;
import com.example.SpringSecurity.Services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/auth")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping ("/register")
    public ResponseEntity<RegisterResponseDto> newRegister(@RequestBody
                                                               RegisterRequestDto requestDto){
        return ResponseEntity.ok(registerService.register(requestDto));
    }
}
