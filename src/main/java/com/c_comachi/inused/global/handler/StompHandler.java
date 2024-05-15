package com.c_comachi.inused.global.handler;

import com.c_comachi.inused.domain.chat.entity.ChatMessage;
import com.c_comachi.inused.domain.chat.repository.ChatRoomRepository;
import com.c_comachi.inused.domain.users.jwt.TokenProvider;
import java.util.Objects;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT == accessor.getCommand()) {
            String jwtToken = accessor.getFirstNativeHeader("token");
            log.info("CONNECT {}", jwtToken);
            tokenProvider.validateToken(Objects.requireNonNull(jwtToken));
        }

        else if (StompCommand.DISCONNECT == accessor.getCommand()) { // Websocket 연결 종료
            String jwtToken = accessor.getFirstNativeHeader("token");
            log.info("DISCONNECT {}", jwtToken);
        }

        return message;
    }
}