package com.example.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "role")
    private String role;

    @OneToOne(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RefreshTokenEntity refreshToken;
}
