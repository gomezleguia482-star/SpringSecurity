package com.example.SpringSecurity.Jwt;

import com.example.SpringSecurity.Services.UserDetailsServicesImplement;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    private final UserDetailsServicesImplement userDetailsServicesImplement;
    private final JwtAccessToken jwtAccessToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain filterChain) throws ServletException, IOException {


        // Extraemos el header de Authorization
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;

        // Validar si el header existe, y es tipo "Bearer"
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // Se corta solo el String para obtener solo el token
        jwt = authHeader.substring(7);

        try{

            //Se parsea el token y se (valida la firma y la expiration)
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtAccessToken.getSignWithKey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            email = claims.getSubject();
             // SI el usuario existe y no esta authenticado en el sistema
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){

                // Buscamos el usuario en la DB
                UserDetails user = userDetailsServicesImplement.loadUserByUsername(email);

                // Creamos el objeto de authentication de Spring Security
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

                // Añadimos los detalles adicionales a la peticion (IP, sesion)
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Autorizado metemos el usuario en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e){
            // Si el token es falso o expiró, no hacemos nada.
            // Spring Security bloqueará la petición más adelante.
            System.err.print("Error de authentication " + e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
