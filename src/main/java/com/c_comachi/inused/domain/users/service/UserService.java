package com.c_comachi.inused.domain.users.service;

import com.c_comachi.inused.domain.users.dto.UserEditInfo;
import com.c_comachi.inused.domain.users.dto.response.GetLoginUserResponseDto;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.common.ErrorCode;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    private  final UserRepository userRepository;

    public ResponseEntity<? super GetLoginUserResponseDto> getLoginUser(String email) {

        UserEntity userEntity = null;

        userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        return GetLoginUserResponseDto.success(userEntity);
    }

    public void editUser(String email, UserEditInfo info) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        user.userEdit(info);
        userRepository.save(user);
    }

    public void deleteUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(userEntity);
    }

}
