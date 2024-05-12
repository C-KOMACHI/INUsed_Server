package com.c_comachi.inused.domain.chat.service;

import com.c_comachi.inused.domain.chat.dto.request.CreateChatRoomRequestDto;
import com.c_comachi.inused.domain.chat.dto.response.ViewAllChatRoomResponseDto;
import com.c_comachi.inused.domain.chat.dto.response.ViewChatRoomResponseDto;
import com.c_comachi.inused.domain.chat.entity.ChatRoom;
import com.c_comachi.inused.domain.chat.repository.ChatRoomRepository;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.exception.AuthenticationException;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import com.c_comachi.inused.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    // Redis
//    private static final String CHAT_ROOMS = "CHAT_ROOM";
//    private final RedisTemplate<String, Object> redisTemplate;
    private final PostRepository postRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    //private HashOperations<String, String, ChatRoom> opsHashChatRoom;

    // 모든 채팅방 조회
    public ResponseEntity<? super ViewAllChatRoomResponseDto> findAllRoom(UserDetails userDetails) {
        if(userDetails == null){
            throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN);
        }
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByOrderByUserId(user);

        return ViewAllChatRoomResponseDto.success(chatRooms);
    }

    public ResponseEntity<? super ViewChatRoomResponseDto> findRoom(Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        return ViewChatRoomResponseDto.success(chatRoom);
    }

    // 채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
    public ChatRoom createChatRoom(@AuthenticationPrincipal UserDetails user, CreateChatRoomRequestDto createChatRoomRequestDto) {
        if(user == null){
            throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN);
        }

        UserEntity sender = userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        UserEntity receiver = userRepository.findById(createChatRoomRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        PostEntity post = postRepository.findById(createChatRoomRequestDto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));

        ChatRoom chatRoom = new ChatRoom(sender, receiver, post);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }


//    @PostConstruct
//    private void init() {
//        //opsHashChatRoom = redisTemplate.opsForHash();
//    }


//    // 특정 채팅방 조회
//    public ChatRoom findRoomById(Long chatroomId) {
//        return opsHashChatRoom.get(CHAT_ROOMS, id);
//    }


}
