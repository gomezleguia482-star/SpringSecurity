package com.example.SpringSecurity.Dto.Request;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
