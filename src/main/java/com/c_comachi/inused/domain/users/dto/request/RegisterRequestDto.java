package com.c_comachi.inused.domain.users.dto.request;

import com.c_comachi.inused.domain.users.entity.Authority;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    private final String EMAIL_ADDRESS = "@inu.ac.kr";

    public RegisterRequestDto(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        return UserEntity.builder()
                .email(email+EMAIL_ADDRESS)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .fireTemperature(36.5)
                .authority(Authority.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
