package com.c_comachi.inused.domain.chat.controller;

import com.c_comachi.inused.domain.chat.LoginInfo;
import com.c_comachi.inused.domain.chat.dto.request.CreateChatRoomRequestDto;
import com.c_comachi.inused.domain.chat.dto.response.ViewAllChatRoomResponseDto;
import com.c_comachi.inused.domain.chat.dto.response.ViewChatRoomResponseDto;
import com.c_comachi.inused.domain.chat.entity.ChatRoom;
import com.c_comachi.inused.domain.chat.repository.ChatRoomRepository;
import com.c_comachi.inused.domain.chat.service.ChatRoomService;
import com.c_comachi.inused.domain.inquiry.dto.response.GetAllUserInquiryResponseDto;
import com.c_comachi.inused.domain.users.dto.response.TokenDto;
import com.c_comachi.inused.domain.users.jwt.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final TokenProvider tokenProvider;

//    @GetMapping("/rooms")
//    @ResponseBody
//    public <? super ViewAllChatRoomResponseDto> viewAllRooms() {
//
//
//        return chatRoomRepository.findAllByOrderByUserId();
//    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채팅방 조회(전체) 성공", content = @Content(schema = @Schema(implementation = ViewAllChatRoomResponseDto.class))),
    })
    @Operation(summary = "채팅방 조회 (전체 조회)")
    @GetMapping("/rooms")
    public ResponseEntity<? super ViewAllChatRoomResponseDto> ViewAllChatroom(@AuthenticationPrincipal UserDetails user) {
        ResponseEntity<? super ViewAllChatRoomResponseDto> response = chatRoomService.findAllRoom(user.getUsername());
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채팅방 만들기 성공")
    })
    @PostMapping("/room")
    public ChatRoom createRoom(@AuthenticationPrincipal UserDetails user, @RequestBody @Valid CreateChatRoomRequestDto requestDto) {
        return chatRoomService.createChatRoom(user, requestDto.getUserId());
    }

//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "채팅방 조회(단건) 성공", content = @Content(schema = @Schema(implementation = ViewChatRoomResponseDto.class))),
//    })
//    @Operation(summary = "채팅방 조회 (단건 조회)")
//    @GetMapping("/room/{roomId}")
//    public ResponseEntity<? super ViewChatRoomResponseDto> roomInfo(@PathVariable(value = "roomId") Long roomId) {
//        ResponseEntity<? super ViewChatRoomResponseDto> response = chatRoomService.findRoom(roomId);
//        return response;
//    }

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

