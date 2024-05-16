package com.c_comachi.inused.domain.wish.service;

import com.c_comachi.inused.domain.post.dto.request.CreatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.response.CreatePostResponseDto;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.domain.wish.dto.request.CreateWishRequestDto;
import com.c_comachi.inused.domain.wish.dto.response.CreateWishResponseDto;
import com.c_comachi.inused.domain.wish.dto.response.DeleteWishResponseDto;
import com.c_comachi.inused.domain.wish.entity.WishEntity;
import com.c_comachi.inused.domain.wish.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishService {
    @Autowired
    private WishRepository wishRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public ResponseEntity<? super CreateWishResponseDto> addWish(CreateWishRequestDto requestsDto,UserEntity user,PostEntity post) {
        //UserEntity user = userRepository.findById(userId).get();
                //.orElseThrow(() -> new RuntimeException("User not found"));
        //PostEntity post = postRepository.findById().get();
                //.orElseThrow(() -> new RuntimeException("Post not found"));
        WishEntity wish = requestsDto.toWish(user,postRepository);
        wishRepository.save(wish);
        return CreateWishResponseDto.success(user, post);
    }

    //    public void addWish(UserEntity userEntity, PostEntity postEntity) {
//        if (!wishRepository.existsByUserIdAndPostId(userEntity.getId(), postEntity.getId())) {
//            WishEntity wish = WishEntity.builder()
//                    .user(userEntity)
//                    .post(postEntity)
//                    .build();
//            wishRepository.save(wish);
//        }
//    }

    @Transactional
    public ResponseEntity<? super DeleteWishResponseDto> deleteWish(PostEntity post, UserEntity user){
        final WishEntity wish = wishRepository.findByUserIdAndPostId(user.getId(),post.getId()).get();
        wishRepository.deleteById(wish.getId());
        return DeleteWishResponseDto.success();
    }
}
