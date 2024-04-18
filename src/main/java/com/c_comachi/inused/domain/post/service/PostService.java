package com.c_comachi.inused.domain.post.service;

import com.c_comachi.inused.domain.post.dto.request.CreatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.request.UpdatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.response.CreatePostResponseDto;
import com.c_comachi.inused.domain.post.dto.response.DeletePostResponseDto;
import com.c_comachi.inused.domain.post.dto.response.GetPostResponseDto;
import com.c_comachi.inused.domain.post.dto.response.UpdatePostResponseDto;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.post.repository.CategoryRepository;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postsRepository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<? super GetPostResponseDto> getPost(Long postId){
        PostEntity post = postsRepository.findById(postId).get();

        post.setViewCount(post.getViewCount()+1);
        postsRepository.save(post);

        return GetPostResponseDto.success(post);
    }

    @Transactional
    public ResponseEntity<? super CreatePostResponseDto> createPost(String email, CreatePostRequestDto requestsDto) {
        UserEntity user = userRepository.findByEmail(email).get();
        PostEntity post = requestsDto.toPost(user, categoryRepository);
        postsRepository.save(post);
        return CreatePostResponseDto.success(post, user);
    }

    @Transactional
    public ResponseEntity<? super UpdatePostResponseDto> updatePost(Long postId, UpdatePostRequestDto requestsDto){
        PostEntity post = postsRepository.findById(postId).get();
        post.update(requestsDto, categoryRepository);
        postsRepository.save(post);
        return UpdatePostResponseDto.success(post);
    }

    @Transactional
    public ResponseEntity<? super DeletePostResponseDto> deletePost(Long postId){
        final PostEntity post = postsRepository.findById(postId).get();
                //.orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        postsRepository.deleteById(postId);
        return DeletePostResponseDto.success();
    }

}