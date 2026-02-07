package com.example.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table (name = "refreshtoken")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_refresh_token")
    private long id;

    @Column (name = "refresh_token")
    private String refreshToken;

    @OneToOne
    @JoinColumn (name = "id_user", referencedColumnName = "id")
    private UserEntity userEntity;

    @Column (name = "date_expiration")
    private Date expiration;

}
