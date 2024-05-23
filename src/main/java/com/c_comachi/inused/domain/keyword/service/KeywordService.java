package com.c_comachi.inused.domain.keyword.service;

import com.c_comachi.inused.domain.keyword.dto.request.CreateKeywordRequestDto;
import com.c_comachi.inused.domain.keyword.dto.request.DeleteKeywordRequestDto;
import com.c_comachi.inused.domain.keyword.dto.response.GetKeyWordResponseDto;
import com.c_comachi.inused.domain.keyword.entity.KeywordEntity;
import com.c_comachi.inused.domain.keyword.repository.KeywordRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.common.ErrorCode;
import com.c_comachi.inused.global.dto.ResponseDto;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;

    public ResponseEntity<ResponseDto> createKeyword(CreateKeywordRequestDto keywordRequestDto, UserDetails userDetails) {
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        KeywordEntity keywordEntity = keywordRequestDto.toKeywordEntity(user);
        keywordRepository.save(keywordEntity);

        return ResponseDto.suc();
    }

    public ResponseEntity<ResponseDto> deleteKeyword(DeleteKeywordRequestDto requestDto) {
        KeywordEntity keyword = keywordRepository.findById(requestDto.getKeywordId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));

        keywordRepository.delete(keyword);

        return ResponseDto.suc();
    }

    public ResponseEntity<? super GetKeyWordResponseDto> getKeyword(UserDetails userDetails){
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        List<KeywordEntity> keywordEntities = keywordRepository.findAllByUser(user);

        return GetKeyWordResponseDto.success(keywordEntities);
    }
}
