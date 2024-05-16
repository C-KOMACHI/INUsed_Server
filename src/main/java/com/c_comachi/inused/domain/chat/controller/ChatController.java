package com.c_comachi.inused.domain.chat.controller;


import com.c_comachi.inused.domain.chat.dto.request.ChatMessageRequestDto;
import com.c_comachi.inused.domain.chat.service.ChatMessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Tag(name = "Chat" , description = "Chat 관련 API 모음")
public class ChatController {

    private final ChatMessageService chatMessageService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(@Header(value = "email") String email, ChatMessageRequestDto chatMessageRequestDto) {

        chatMessageService.sendMessage(chatMessageRequestDto, email);
        // Websocket에 발행된 메시지를 MongoDB 로 발행
    }

}
