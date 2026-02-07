package com.example.SpringSecurity.Services;

import com.example.SpringSecurity.Config.SecurityConfig;
import com.example.SpringSecurity.Dto.Request.RegisterRequestDto;
import com.example.SpringSecurity.Dto.Response.RegisterResponseDto;
import com.example.SpringSecurity.Entity.UserEntity;
import com.example.SpringSecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public RegisterResponseDto register(RegisterRequestDto requestDto){
        try{
            UserEntity user = UserEntity.builder()
                    .email(requestDto.getEmail())
                    .password(securityConfig.passwordEncoder().encode(requestDto.getPassword()))
                    .role("USER")
                    .build();

            userRepository.save(user);

            RegisterResponseDto responseDto = RegisterResponseDto.builder()
                    .email(user.getEmail())
                    .message("Registro exitoso")
                    .build();
            return responseDto;
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("Error en el registro");
            return null;
        }
    }
}
