package com.c_comachi.inused.domain.chat.service;

import com.c_comachi.inused.domain.chat.dto.request.ChatMessageRequestDto;
import com.c_comachi.inused.domain.chat.entity.ChatMessage;
import com.c_comachi.inused.domain.chat.repository.ChatMessageRepository;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;
    private final ChatMessageRepository chatMessageRepository;

    public void sendMessage(ChatMessageRequestDto chatMessageRequestDto, String nickname){
        chatMessageRequestDto.setSender(nickname);

        ChatMessage chatMessage = chatMessageRequestDto.toChatMessage();

        chatMessageRepository.save(chatMessage);
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }
}
