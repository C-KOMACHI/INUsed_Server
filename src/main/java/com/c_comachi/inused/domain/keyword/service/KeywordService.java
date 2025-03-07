package com.c_comachi.inused.domain.keyword.service;

import com.c_comachi.inused.domain.keyword.dto.KeywordInfo;
import com.c_comachi.inused.domain.keyword.dto.KeywordPost;
import com.c_comachi.inused.domain.keyword.dto.request.CreateKeywordRequestDto;
import com.c_comachi.inused.domain.keyword.dto.request.DeleteKeywordRequestDto;
import com.c_comachi.inused.domain.keyword.dto.response.GetKeyWordResponseDto;
import com.c_comachi.inused.domain.keyword.dto.response.GetKeywordPostResponseDto;
import com.c_comachi.inused.domain.keyword.entity.KeywordEntity;
import com.c_comachi.inused.domain.keyword.repository.KeywordRepository;
import com.c_comachi.inused.domain.post.dto.MainPostInfo;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.common.ErrorCode;
import com.c_comachi.inused.global.dto.ResponseDto;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import com.c_comachi.inused.global.exception.InvalidValueException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<ResponseDto> createKeyword(CreateKeywordRequestDto keywordRequestDto, UserDetails userDetails) {
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        KeywordEntity keywordEntity = keywordRequestDto.toKeywordEntity(user);
        if(keywordRepository.existsByUserAndName(user, keywordRequestDto.getKeyword())) {
            throw new InvalidValueException(ErrorCode.DUPLICATED_KEYWORD);
        }

        keywordRepository.save(keywordEntity);

        return ResponseDto.suc();
    }

    @Transactional
    public ResponseEntity<ResponseDto> deleteKeyword(DeleteKeywordRequestDto requestDto) {
        KeywordEntity keyword = keywordRepository.findById(requestDto.getKeywordId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));

        keywordRepository.delete(keyword);

        return ResponseDto.suc();
    }

    @Transactional
    public ResponseEntity<? super GetKeyWordResponseDto> getKeyword(UserDetails userDetails){
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        List<KeywordEntity> keywordEntities = keywordRepository.findAllByUser(user);
        List<KeywordInfo> keywordInfos = new ArrayList<>();
        for (KeywordEntity keywordEntity : keywordEntities) {
            KeywordInfo keywordInfo = new KeywordInfo(keywordEntity);
            keywordInfos.add(keywordInfo);
        }

        return GetKeyWordResponseDto.success(keywordInfos);
    }

    @Transactional
    public ResponseEntity<? super GetKeywordPostResponseDto> getKeywordPost(UserDetails userDetails){
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        List<KeywordEntity> keywordEntities = keywordRepository.findAllByUser(user);
        List<KeywordPost> keywordPosts = new ArrayList<>();

        Specification<PostEntity> spec = titleContainsKeywords(keywordEntities);
        List<PostEntity> postEntities = postRepository.findAll(spec);

        for(PostEntity post: postEntities){
            for(KeywordEntity keyword : keywordEntities){
                if(post.getTitle().contains(keyword.getName()))
                    keywordPosts.add(new KeywordPost(post, keyword.getName()));
            }
        }

        return GetKeywordPostResponseDto.success(keywordPosts);
    }

    public Specification<PostEntity> titleContainsKeywords(List<KeywordEntity> keywords) {
        return (Root<PostEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (KeywordEntity keyword : keywords) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + keyword.getName().toLowerCase() + "%"));
            }
            query.orderBy(cb.desc(root.get("lastReposting"))); // 내림차순 정렬
            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
