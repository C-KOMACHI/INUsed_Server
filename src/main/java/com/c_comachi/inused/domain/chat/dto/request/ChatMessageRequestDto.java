package com.c_comachi.inused.domain.chat.dto.request;

import com.c_comachi.inused.domain.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ChatMessageRequestDto {
    private Long roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지


    public ChatMessageRequestDto(Long roomId, String sender, String message){
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

    public ChatMessage toChatMessage(){
        return ChatMessage.builder()
                .roomId(roomId)
                .sender(sender)
                .message(message)
                .build();
    }

}
