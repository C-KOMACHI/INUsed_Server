package com.c_comachi.inused.domain.users.dto;

import com.c_comachi.inused.domain.users.entity.Authority;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private String email;


    private String nickname;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 3, max = 100)
    private String password;

    public static UserDto from(UserEntity user) {
        if(user == null) return null;

        return UserDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

}
