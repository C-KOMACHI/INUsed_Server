package com.c_comachi.inused.domain.chat.service;

import com.c_comachi.inused.domain.chat.dto.response.ViewAllChatRoomResponseDto;
import com.c_comachi.inused.domain.chat.dto.response.ViewChatRoomResponseDto;
import com.c_comachi.inused.domain.chat.entity.ChatRoom;
import com.c_comachi.inused.domain.chat.repository.ChatRoomRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    // Redis
//    private static final String CHAT_ROOMS = "CHAT_ROOM";
//    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    //private HashOperations<String, String, ChatRoom> opsHashChatRoom;

    // 모든 채팅방 조회
    public ResponseEntity<? super ViewAllChatRoomResponseDto> findAllRoom(String email) {
        UserEntity user = userRepository.findByEmail(email).get();
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByOrderByUserId(user);

        return ViewAllChatRoomResponseDto.success(chatRooms);
        //return opsHashChatRoom.values(CHAT_ROOMS);
    }

    public ResponseEntity<? super ViewChatRoomResponseDto> findRoom(Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        return ViewChatRoomResponseDto.success(chatRoom);
    }

    // 채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
    public ChatRoom createChatRoom(@AuthenticationPrincipal UserDetails user, Long userId) {
        UserEntity sender = userRepository.findByEmail(user.getUsername()).get();
        UserEntity receiver = userRepository.findById(userId).get();

        ChatRoom chatRoom = new ChatRoom(sender, receiver);
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
