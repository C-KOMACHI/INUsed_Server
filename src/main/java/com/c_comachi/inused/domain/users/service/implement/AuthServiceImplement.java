package com.c_comachi.inused.domain.users.service.implement;

import com.c_comachi.inused.domain.users.dto.request.*;
import com.c_comachi.inused.domain.users.dto.response.LogoutResponseDto;
import com.c_comachi.inused.domain.users.dto.response.PasswordFindResponseDto;
import com.c_comachi.inused.domain.users.dto.response.ReissueResponseDto;
import com.c_comachi.inused.domain.users.dto.response.TokenDto;
import com.c_comachi.inused.domain.users.service.AuthService;
import com.c_comachi.inused.global.dto.ResponseDto;
import com.c_comachi.inused.domain.users.dto.response.LoginResponseDto;
import com.c_comachi.inused.domain.users.dto.response.RegisterResponseDto;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.jwt.TokenProvider;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.service.RedisService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String EMAIL_ADDRESS = "@inu.ac.kr";

    @Transactional
    @Override
    public ResponseEntity<? super RegisterResponseDto> register(RegisterRequestDto requestDto) {

        try {
            String email = requestDto.getEmail() + EMAIL_ADDRESS;
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail) return RegisterResponseDto.duplicateEmail();

            String nickname = requestDto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return RegisterResponseDto.duplicateNickname();

            UserEntity user = requestDto.toUser(passwordEncoder);
            userRepository.save(user);

        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return RegisterResponseDto.success();
    }

    @Transactional
    @Override
    public ResponseEntity<? super LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        TokenDto tokenDto = null;

        try {
            // 0. id 검증
            Optional<UserEntity> email = userRepository.findByEmail(loginRequestDto.getEmail());
            if(email.isEmpty()) return LoginResponseDto.loginFailed();

            // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
            UsernamePasswordAuthenticationToken authenticationToken = loginRequestDto.toAuthentication();

            // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
            //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            tokenDto = tokenProvider.generateTokenDto(authentication);

            // 4. Redis 에 RefreshToken 저장
            long refreshTokenExpirationMillis = tokenProvider.getRefreshTokenExpirationMillis();
            redisService.setValues(authentication.getName(), tokenDto.getRefreshToken(), Duration.ofMillis(refreshTokenExpirationMillis));

        } catch (UsernameNotFoundException | BadCredentialsException | LockedException e) {
            return LoginResponseDto.loginFailed();
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 5. 토큰 발급
        return LoginResponseDto.success(tokenDto);
    }

    @Transactional
    @Override
    public ResponseEntity<? super ReissueResponseDto> reissue(TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto = null;

        try {
            // 1. Refresh Token 검증
            if (verifiedRefreshToken(tokenRequestDto)) {
                return ReissueResponseDto.validationFailed(); // VF
            }
            // 2. Access Token 에서 User ID 가져오기
            Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

            // 3. 저장소에서 User ID 를 기반으로 Refresh Token 값 가져옴
            String refreshToken = redisService.getValues(authentication.getName());

            // 4. Refresh Token 일치하는지 검사
            if (!refreshToken.equals(tokenRequestDto.getRefreshToken())) {
                return ReissueResponseDto.mismatchedToken(); // MT
            }

            // 5. 새로운 토큰 생성
            tokenDto = tokenProvider.generateTokenDto(authentication);

            // 6. 저장소 정보 업데이트
            long refreshTokenExpirationMillis = tokenProvider.getRefreshTokenExpirationMillis();
            redisService.setValues(authentication.getName(), tokenDto.getRefreshToken(), Duration.ofMillis(refreshTokenExpirationMillis));

        } catch(RuntimeException runtimeException) {
            return ReissueResponseDto.loggedOutUser();
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        // 토큰 발급
        return ReissueResponseDto.success(tokenDto);
    }

    @Transactional
    @Override
    public ResponseEntity<? super LogoutResponseDto> logout(TokenRequestDto tokenRequestDto) {
        try {
            // 1. Refresh Token 검증
            if (verifiedRefreshToken(tokenRequestDto)) {
                return LogoutResponseDto.validationFailed(); // VF
            }
            // 2. Access Token 에서 User ID 가져오기
            Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

            // 3. key 값이 User ID를 통해서 value 가져오기
            String redisRefreshToken = redisService.getValues(authentication.getName());

            if(redisService.checkExistsValue(redisRefreshToken)){
                // 4. Value 값인 refreshToken 삭제
                redisService.deleteValues(authentication.getName());

                // 5. accessToken 블랙리스트 등록
                long accessTokenExpirationMillis  = tokenProvider.getAccessTokenExpirationMillis();
                redisService.setValues(tokenRequestDto.getAccessToken(), "logout", Duration.ofMillis(accessTokenExpirationMillis));
            }

        } catch (Exception exception){
            exception.printStackTrace();
        }

        return LogoutResponseDto.success();
    }

    @Transactional
    @Override
    public ResponseEntity<? super RegisterResponseDto> nicknameCheck(NicknameRequestDto nicknameRequestDto) {
        boolean existedNickname = userRepository.existsByNickname(nicknameRequestDto.getNickname());
        if (existedNickname) return RegisterResponseDto.duplicateNickname();

        return RegisterResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PasswordFindResponseDto> passwordFinder(PasswordFindRequestDto passwordFindRequestDto) {
        try{
            if(!userRepository.existsByEmail(passwordFindRequestDto.getEmail())){
                return PasswordFindResponseDto.notExistedEmail();
            }

            UserEntity user = userRepository.findByEmail(passwordFindRequestDto.getEmail()).get();


            if(passwordEncoder.matches(passwordFindRequestDto.getPassword(), user.getPassword())){
                return PasswordFindResponseDto.samePassword();
            }


            user.passwordChange(passwordEncoder, passwordFindRequestDto.getPassword());
            userRepository.save(user);
        } catch (Exception e){
            e.printStackTrace();
        }

        return PasswordFindResponseDto.success();
    }


    private boolean verifiedRefreshToken(TokenRequestDto tokenRequestDto) {
        return !tokenProvider.validateToken(tokenRequestDto.getRefreshToken());
    }

}
