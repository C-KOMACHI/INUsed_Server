package com.c_comachi.inused.domain.chat.service;

import com.c_comachi.inused.domain.chat.dto.request.ChatMessageRequestDto;
import com.c_comachi.inused.domain.chat.entity.ChatMessage;
import com.c_comachi.inused.domain.chat.repository.ChatMessageRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import com.c_comachi.inused.global.exception.ErrorCode;
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

    public void sendMessage(ChatMessageRequestDto chatMessageRequestDto, String email){
        //UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        String nickname = email;
        chatMessageRequestDto.setSender(nickname);

        ChatMessage chatMessage = chatMessageRequestDto.toChatMessage();

        chatMessageRepository.save(chatMessage);
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }
}
