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
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    // Redis CacheKeys
    public static final String ENTER_INFO = "ENTER_INFO"; // 채팅룸에 입장한 클라이언트의 sessionId와 채팅룸 id를 맵핑한 정보 저장

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, ChatRoom> hashOpsChatRoom;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOpsEnterInfo;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOps;

    private final PostRepository postRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    // 모든 채팅방 조회
    public ResponseEntity<? super ViewAllChatRoomResponseDto> findAllRoom(UserDetails userDetails) {
        if(userDetails == null){
            throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN);
        }
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByUser(user);

        return ViewAllChatRoomResponseDto.success(chatRooms);
    }

    public ResponseEntity<? super ViewChatRoomResponseDto> findRoom(Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        return ViewChatRoomResponseDto.success(chatRoom);
    }

    // 채팅방 생성 : 서버간 채팅방 공유를 위해 MariaDB 에 저장
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

    // 유저가 입장한 채팅방ID와 유저 세션ID 맵핑 정보 저장
    public void setUserEnterInfo(String sessionId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
    }

    // 유저 세션으로 입장해 있는 채팅방 ID 조회
    public String getUserEnterRoomId(String sessionId) {
        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
    }

    // 유저 세션정보와 맵핑된 채팅방ID 삭제
    public void removeUserEnterInfo(String sessionId) {
        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
    }

    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }



}
