package com.c_comachi.inused.domain.users.controller;

import com.c_comachi.inused.domain.users.dto.request.UserEditRequestDto;
import com.c_comachi.inused.domain.users.dto.response.GetLoginUserResponseDto;
import com.c_comachi.inused.domain.users.dto.response.UserEditResponseDto;
import com.c_comachi.inused.domain.users.service.UserService;
import com.c_comachi.inused.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<? super GetLoginUserResponseDto> getUser(Authentication authentication) {
        ResponseEntity<? super GetLoginUserResponseDto> response = userService.getLoginUser(authentication.getName());
        return response;
    }

    @PatchMapping("/edit")
    public ResponseEntity<ResponseDto> editUser(Authentication authentication, @RequestBody @Valid UserEditRequestDto requestDto) {
        userService.editUser(authentication.getName(), requestDto.toUserEditInfo());
        return ResponseDto.suc();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return ResponseDto.suc();
    }
}
