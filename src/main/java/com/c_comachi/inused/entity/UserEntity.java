package com.c_comachi.inused.entity;

import com.c_comachi.inused.dto.request.auth.RegisterRequestDto;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="users")
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    private String profileImage;

    private Double fireTemperature;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    @Builder
    public UserEntity(String email, String password, String nickname, Double fireTemperature, Authority authority,LocalDateTime createdAt){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.fireTemperature = fireTemperature;
        this.authority = authority;
        this.createdAt = createdAt;
    }
}
