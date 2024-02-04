package com.c_comachi.inused.dto.request.auth;

import com.c_comachi.inused.entity.Authority;
import com.c_comachi.inused.entity.UserEntity;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank @Email
    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        return UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .fireTemperature(36.5)
                .authority(Authority.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
