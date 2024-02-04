package com.c_comachi.inused.service.implement;

import com.c_comachi.inused.dto.response.auth.ReissueResponseDto;
import com.c_comachi.inused.dto.response.auth.TokenDto;
import com.c_comachi.inused.dto.request.auth.RegisterRequestDto;
import com.c_comachi.inused.dto.request.auth.LoginRequestDto;
import com.c_comachi.inused.dto.request.token.TokenRequestDto;
import com.c_comachi.inused.dto.response.ResponseDto;
import com.c_comachi.inused.dto.response.auth.LoginResponseDto;
import com.c_comachi.inused.dto.response.auth.RegisterResponseDto;
import com.c_comachi.inused.entity.RefreshToken;
import com.c_comachi.inused.entity.UserEntity;
import com.c_comachi.inused.jwt.TokenProvider;
import com.c_comachi.inused.repository.RefreshTokenRepository;
import com.c_comachi.inused.repository.UserRepository;
import com.c_comachi.inused.service.AuthService;
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

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    @Override
    public ResponseEntity<? super RegisterResponseDto> register(RegisterRequestDto requestDto) {

        try {
            String email = requestDto.getEmail();
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
            // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
            UsernamePasswordAuthenticationToken authenticationToken = loginRequestDto.toAuthentication();

            // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
            //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            tokenDto = tokenProvider.generateTokenDto(authentication);

            // 4. RefreshToken 저장
            RefreshToken refreshToken = RefreshToken.builder()
                    .key(authentication.getName())
                    .value(tokenDto.getRefreshToken())
                    .build();

            refreshTokenRepository.save(refreshToken);

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
    public ResponseEntity<? super ReissueResponseDto> reissue(TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto = null;

        try {
            // 1. Refresh Token 검증
            if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
                return ReissueResponseDto.validationFailed(); // VF
            }
            // 2. Access Token 에서 User ID 가져오기
            Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

            // 3. 저장소에서 User ID 를 기반으로 Refresh Token 값 가져옴
            RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                    .orElseThrow(() -> new RuntimeException()); // LU

            // 4. Refresh Token 일치하는지 검사
            if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
                return ReissueResponseDto.mismatchedToken(); // MT
            }

            // 5. 새로운 토큰 생성
            tokenDto = tokenProvider.generateTokenDto(authentication);

            // 6. 저장소 정보 업데이트
            RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
            refreshTokenRepository.save(newRefreshToken);

        } catch(RuntimeException runtimeException) {
            return ReissueResponseDto.loggedOutUser();
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }


        // 토큰 발급
        return ReissueResponseDto.success(tokenDto);
    }


}
