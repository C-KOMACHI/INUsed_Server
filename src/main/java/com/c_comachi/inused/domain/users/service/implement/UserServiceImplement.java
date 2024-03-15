package com.c_comachi.inused.domain.users.service.implement;

import com.c_comachi.inused.domain.users.dto.UserDto;
import com.c_comachi.inused.domain.users.dto.response.GetLoginUserResponseDto;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.domain.users.service.UserService;
import com.c_comachi.inused.domain.users.util.SecurityUtil;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private  final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetLoginUserResponseDto> getLoginUser(String userId) {

        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findById(Long.parseLong(userId)).get();
            if(userEntity == null) return GetLoginUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetLoginUserResponseDto.success(userEntity);
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String email) {
         return UserDto.from(userRepository.findByEmail(email).orElse(null));
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentMemberId().flatMap(userRepository::findById);
    }

}
