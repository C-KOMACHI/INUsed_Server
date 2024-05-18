package com.c_comachi.inused.domain.users.entity;

import com.c_comachi.inused.domain.users.dto.UserEditInfo;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
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

    @Column(nullable = false, name = "profile_image")
    private String profileImage;

    @Setter
    @Column(name = "fire_temperature", nullable = false)
    private Integer fireTemperature;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @CreatedDate
    @Column(updatable = false, nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Builder
    public UserEntity(String email, String password, String nickname, Integer fireTemperature, Authority authority,LocalDateTime createdAt){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.fireTemperature = fireTemperature;
        this.authority = authority;
        this.createdAt = createdAt;
    }

    public void userEdit(UserEditInfo info){
        this.profileImage = info.getProfileImage();
        this.nickname = info.getNickname();
    }

    public void passwordChange(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);

    }
}
