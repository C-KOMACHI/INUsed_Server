package com.c_comachi.inused.domain.wish.service;

import com.c_comachi.inused.domain.post.dto.MainPostInfo;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.domain.wish.dto.request.CreateWishRequestDto;
import com.c_comachi.inused.domain.wish.dto.response.GetWishesResponseDto;
import com.c_comachi.inused.domain.wish.entity.WishEntity;
import com.c_comachi.inused.domain.wish.repository.WishRepository;
import com.c_comachi.inused.global.common.ErrorCode;
import com.c_comachi.inused.global.dto.ResponseDto;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WishService {
    private final WishRepository wishRepository;
    private final  PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<ResponseDto> addWish(CreateWishRequestDto requestsDto, UserDetails user) {
        UserEntity userEntity = userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        PostEntity post = postRepository.findById(requestsDto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));

        // 좋아요 정보가 없으면 추가
        if(!wishRepository.existsByUserAndPost(user.getUsername(), requestsDto.getPostId())) {
            WishEntity wish = requestsDto.toWish(userEntity ,postRepository);
            post.setWishCount(post.getWishCount()+1);
            wishRepository.save(wish);
            postRepository.save(post);
        }
        // 좋아요 정보가 있으면 삭제
        else if(wishRepository.existsByUserAndPost(user.getUsername(), requestsDto.getPostId())) {
            WishEntity wish = wishRepository.findByUserIdAndPostId(userEntity.getId(),requestsDto.getPostId()).get();
            post.setWishCount(post.getWishCount()-1);
            wishRepository.deleteById(wish.getId());
            postRepository.save(post);
        }

        return ResponseDto.suc();
    }

    @Transactional
    public ResponseEntity<? super GetWishesResponseDto> getWish(UserDetails user) {
        List<WishEntity> wishEntities = wishRepository.findAllByUserEmail(user.getUsername());
        List<MainPostInfo> mainPostInfos = new ArrayList<>();

        for(WishEntity wishEntity : wishEntities) {
            PostEntity post = wishEntity.getPost();
            MainPostInfo mainPostInfo = new MainPostInfo(post, true);
            mainPostInfos.add(mainPostInfo);
        }

        return GetWishesResponseDto.success(mainPostInfos);
    }

}
