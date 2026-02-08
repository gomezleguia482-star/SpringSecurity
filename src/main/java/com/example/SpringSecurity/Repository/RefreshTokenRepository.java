package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.RefreshTokenEntity;
import com.example.SpringSecurity.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByUserEntity(UserEntity userEntity);

    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);
}
