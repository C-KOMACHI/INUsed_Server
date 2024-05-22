package com.c_comachi.inused.domain.chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateChatRoomRequestDto {
    private Long userId;
    private Long postId;
}
