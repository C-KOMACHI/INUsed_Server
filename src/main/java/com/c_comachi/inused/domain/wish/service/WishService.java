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
import java.util.Optional;
import java.util.stream.Collectors;

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

        Optional<WishEntity> Wish = wishRepository.findByUserAndPost(userEntity, post);

        if(!Wish.isPresent()) {
            WishEntity wish = requestsDto.toWish(userEntity ,postRepository);
            post.setWishCount(post.getWishCount()+1);
            wishRepository.save(wish);
            postRepository.save(post);

            return ResponseDto.suc();
        }

        post.setWishCount(post.getWishCount()-1);
        wishRepository.deleteById(Wish.get().getId());
        postRepository.save(post);


        return ResponseDto.suc();
    }

    @Transactional
    public ResponseEntity<? super GetWishesResponseDto> getWish(UserDetails user) {
        List<WishEntity> wishEntities = wishRepository.findAllByUserEmailOrderByIdDesc(user.getUsername());

        return GetWishesResponseDto.success(getMainPostInfosByWishList(wishEntities, user));
    }

    @Transactional
    public ResponseEntity<? super GetWishesResponseDto> getUserWishes(Long userId, UserDetails user) {

        if(!userRepository.existsById(userId)){
            throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        List<WishEntity> wishEntities = wishRepository.findAllByUserId(userId);

        return GetWishesResponseDto.success(getMainPostInfosByWishList(wishEntities, user));
    }

    public List<MainPostInfo> getMainPostInfosByWishList(List<WishEntity> wishEntities, UserDetails user) {
        return wishEntities.stream()
                .map(wishEntity -> {
                    PostEntity post = wishEntity.getPost();
                    boolean checkMyPost = post.getUser().getEmail().equals(user.getUsername());
                    boolean checkLiked = wishRepository.existsByUserAndPost(user.getUsername(), post.getId());
                    return new MainPostInfo(post, checkLiked, checkMyPost);
                })
                .collect(Collectors.toList());
    }

}
