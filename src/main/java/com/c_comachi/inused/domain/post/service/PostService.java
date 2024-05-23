package com.c_comachi.inused.domain.post.service;

import com.c_comachi.inused.domain.post.dto.MainPostInfo;
import com.c_comachi.inused.domain.post.dto.request.CreatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.request.UpdatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.response.*;
import com.c_comachi.inused.domain.post.repository.CategoryRepository;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.domain.wish.repository.WishRepository;
import com.c_comachi.inused.global.dto.ResponseDto;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import com.c_comachi.inused.global.common.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postsRepository;
    private final CategoryRepository categoryRepository;
    private final WishRepository wishRepository;

    private final Integer RE_POST_LIMIT = 24;

    public ResponseEntity<? super GetPostResponseDto> getPost(Long postId, UserDetails user){
        PostEntity post = postsRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));

        post.setViewCount(post.getViewCount() + 1);
        postsRepository.save(post);

        boolean checkLiked = wishRepository.existsByUserAndPost(user.getUsername(), postId);
        boolean checkMyPost = post.getUser().getEmail().equals(user.getUsername());
        MainPostInfo mainPostInfo = new MainPostInfo(post, checkLiked, checkMyPost);

        return GetPostResponseDto.success(mainPostInfo);
    }

    @Transactional
    public ResponseEntity<? super CreatePostResponseDto> createPost(String email, CreatePostRequestDto requestsDto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        PostEntity post = requestsDto.toPost(user, categoryRepository);
        postsRepository.save(post);
        return CreatePostResponseDto.success(post, user);
    }

    @Transactional
    public ResponseEntity<? super UpdatePostResponseDto> updatePost(Long postId, UpdatePostRequestDto requestsDto){
        PostEntity post = postsRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));

        post.update(requestsDto, categoryRepository);
        postsRepository.save(post);
        return UpdatePostResponseDto.success(post);
    }

    @Transactional
    public ResponseEntity<ResponseDto> deletePost(Long postId){
        postsRepository.deleteById(postId);
        return ResponseDto.suc();
    }

    public ResponseEntity<? super RePostResponseDto> rePost(Long postId){
        PostEntity post = postsRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));

        Duration duration = Duration.between(LocalDateTime.now(), post.getLastReposting());
        if(duration.toHours() < RE_POST_LIMIT){
            return RePostResponseDto.fastRePosting();
        }

        return RePostResponseDto.success();
    }

    public ResponseEntity<? super GetAllPostResponseDto> getAllPost(UserDetails user){
        List<PostEntity> posts = postsRepository.findAllByOrderByLastRepostingDesc();
        List<MainPostInfo> mainPostInfos = postsToMainPosts(posts, user);

        return GetAllPostResponseDto.success(mainPostInfos);
    }

    public ResponseEntity<? super GetAllPostResponseDto> searchPost(UserDetails user, String search){
        List<PostEntity> posts = postsRepository.findAllByTitleContainingOrContentContainingOrderByLastRepostingDesc(search, search);
        List<MainPostInfo> mainPostInfos = postsToMainPosts(posts, user);

        return GetAllPostResponseDto.success(mainPostInfos);
    }

    public ResponseEntity<? super GetAllPostResponseDto> getMyPost(UserDetails user){
        List<PostEntity> posts = postsRepository.findAllByUserEmail(user.getUsername());
        List<MainPostInfo> mainPostInfos = postsToMainPosts(posts, user);

        return GetAllPostResponseDto.success(mainPostInfos);
    }

    public ResponseEntity<? super GetAllPostResponseDto> getUserPost(Long userId, UserDetails userDetails){
        List<PostEntity> posts = postsRepository.findAllByUserId(userId);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        List<MainPostInfo> mainPostInfos = postsToMainPosts(posts, userDetails);

        return GetAllPostResponseDto.success(mainPostInfos);
    }

    public ResponseEntity<? super GetAllPostResponseDto> getCategoryPost(UserDetails user, Long categoryId){
        List<PostEntity> posts = postsRepository.findAllByCategoryId(categoryId);
        List<MainPostInfo> mainPostInfos = postsToMainPosts(posts, user);

        return GetAllPostResponseDto.success(mainPostInfos);
    }

    public List<MainPostInfo> postsToMainPosts(List<PostEntity> posts, UserDetails user){
        List<MainPostInfo> mainPostInfos = new ArrayList<>();

        for(PostEntity post : posts){
            boolean checkLiked = wishRepository.existsByUserAndPost(user.getUsername(), post.getId());
            boolean checkMyPost = post.getUser().getEmail().equals(user.getUsername());
            MainPostInfo mainPostInfo = new MainPostInfo(post,checkLiked, checkMyPost);
            mainPostInfos.add(mainPostInfo);
        }

        return mainPostInfos;
    }

}