package com.c_comachi.inused.domain.users.service;

import com.c_comachi.inused.domain.users.dto.UserDto;
import com.c_comachi.inused.domain.users.dto.response.GetLoginUserResponseDto;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    ResponseEntity<? super GetLoginUserResponseDto> getLoginUser(String userId);

    public UserDto getUserWithAuthorities(String email);

    public Optional<UserEntity> getMyUserWithAuthorities();

}
