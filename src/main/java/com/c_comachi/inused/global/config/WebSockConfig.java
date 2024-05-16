package com.c_comachi.inused.global.config;

import com.c_comachi.inused.global.handler.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSockConfig implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub"); // 해당 주소를 구독하는 클라어인트에게 메시지를 보낸다.
        config.setApplicationDestinationPrefixes("/pub"); // /pub 으로 시작하는 메세지만 Broker에서 처리
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp") // ex ) ws://localhost:8080/ws-stomp
                .setAllowedOrigins("http://localhost:8080") // 접근할 수 있는 권한
                .setAllowedOrigins("http://localhost:5173")
                .setAllowedOrigins("https://api.inused.store")
                .setAllowedOrigins("https://www.inused.store")
                .setAllowedOrigins("https://inused.store")
                .setAllowedOrigins("http://127.0.0.1:5173")
                .withSockJS();
    }

    public void configureClientInboundChannel(ChannelRegistration registration){
        registration.interceptors(stompHandler);
    }
}
