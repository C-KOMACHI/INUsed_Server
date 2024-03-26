package com.c_comachi.inused.domain.users.service.implement;

import com.c_comachi.inused.domain.users.dto.UserEditInfo;
import com.c_comachi.inused.domain.users.dto.response.GetLoginUserResponseDto;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private  final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetLoginUserResponseDto> getLoginUser(String email) {

        UserEntity userEntity = null;

        userEntity = userRepository.findByEmail(email).get();
        if(userEntity == null) return GetLoginUserResponseDto.notExistUser();


        return GetLoginUserResponseDto.success(userEntity);
    }

    @Override
    public void editUser(String email, UserEditInfo info) {
        UserEntity user = userRepository.findByEmail(email).get();
        user.userEdit(info);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).get();
        userRepository.delete(userEntity);
    }

}
