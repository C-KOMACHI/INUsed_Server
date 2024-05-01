package com.c_comachi.inused.domain.chat.controller;

import com.c_comachi.inused.domain.chat.LoginInfo;
import com.c_comachi.inused.domain.chat.moel.ChatRoom;
import com.c_comachi.inused.domain.chat.repository.ChatRoomRepository;
import com.c_comachi.inused.domain.users.dto.response.TokenDto;
import com.c_comachi.inused.domain.users.jwt.TokenProvider;
import java.util.List;

import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final TokenProvider tokenProvider;

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoom(name);
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    @GetMapping("/user")
    @ResponseBody
    public LoginInfo getUserInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email =  auth.getName();
        TokenDto tokenDto = tokenProvider.generateTokenDto(auth);
        String token = tokenDto.getAccessToken();
        return LoginInfo.builder().email(email).token(token).build();
    }
}

