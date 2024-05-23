package com.c_comachi.inused.domain.users.service;

import com.c_comachi.inused.domain.users.dto.UserEditInfo;
import com.c_comachi.inused.domain.users.dto.response.GetUserResponseDto;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.common.ErrorCode;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    private  final UserRepository userRepository;

    @Transactional
    public ResponseEntity<? super GetUserResponseDto> getLoginUser(String email) {

        UserEntity userEntity = null;

        userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        return GetUserResponseDto.success(userEntity);
    }

    @Transactional
    public void editUser(String email, UserEditInfo info) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        user.userEdit(info);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(userEntity);
    }

    @Transactional
    public ResponseEntity<? super GetUserResponseDto> getUser(Long userId) {

        UserEntity userEntity = null;

        userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        return GetUserResponseDto.success(userEntity);

    }

}
