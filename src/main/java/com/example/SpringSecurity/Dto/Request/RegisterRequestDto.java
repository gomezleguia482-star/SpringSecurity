package com.example.SpringSecurity.Dto.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegisterRequestDto {
    private String email;
    private String password;
}
