package com.c_comachi.inused.domain.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NicknameRequestDto {
    @NotBlank
    private String nickname;
}
