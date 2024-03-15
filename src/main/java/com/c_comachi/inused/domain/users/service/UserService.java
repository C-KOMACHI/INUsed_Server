package com.c_comachi.inused.domain.users.service;

import com.c_comachi.inused.domain.users.dto.UserEditInfo;
import com.c_comachi.inused.domain.users.dto.response.GetLoginUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetLoginUserResponseDto> getLoginUser(String email);

    void editUser(String email, UserEditInfo requestDto);

    void deleteUser(String email);


}
