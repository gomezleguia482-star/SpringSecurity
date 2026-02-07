package com.example.SpringSecurity.Services;

import com.example.SpringSecurity.Dto.Request.LoginRequestDto;
import com.example.SpringSecurity.Dto.Response.LoginResponseDto;
import com.example.SpringSecurity.Entity.UserEntity;
import com.example.SpringSecurity.Jwt.JwtAccessToken;
import com.example.SpringSecurity.Jwt.JwtRefreshToken;
import com.example.SpringSecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtAccessToken jwtAccessToken;
    private final JwtRefreshToken jwtRefreshToken;
    private final UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto){

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getEmail(), loginRequestDto.getPassword()
                    )
            );

            UserEntity user = userRepository.findByEmail(loginRequestDto.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            String accessToken = jwtAccessToken.generationAccessToken(
                    user.getEmail(),
                    user.getRole());

            String refreshToken = jwtRefreshToken.generationAccessToken(
                    user.getEmail(),
                    user.getRole()
            );

            return LoginResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

        }catch (BadCredentialsException e){
            throw new RuntimeException("Credenciales invalidas intentalo de nuevo");
        } catch (Exception e) {
            e.printStackTrace();
        // Error genérico por si algo más falla
            throw new RuntimeException("Ocurrió un error inesperado durante el login.");
    }
    }
}
