package com.c_comachi.inused.domain.post.controller;

import com.c_comachi.inused.domain.post.dto.request.CreatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.request.UpdatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.response.CreatePostResponseDto;
import com.c_comachi.inused.domain.post.dto.response.DeletePostResponseDto;
import com.c_comachi.inused.domain.post.dto.response.GetPostResponseDto;
import com.c_comachi.inused.domain.post.dto.response.UpdatePostResponseDto;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.post.service.PostService;
import com.c_comachi.inused.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<? super CreatePostResponseDto> createPost(@RequestBody @Valid CreatePostRequestDto requestsDto, Authentication authentication) {
        ResponseEntity<? super CreatePostResponseDto> response = postService.createPost(authentication.getName(), requestsDto);
        return response;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<? super GetPostResponseDto> getPost(@PathVariable("postId") Long postId) {
        ResponseEntity<? super GetPostResponseDto> response = postService.getPost(postId);
        return response;
    }
    @PatchMapping("/{postId}/update")
    public ResponseEntity<? super UpdatePostResponseDto> updatePost(@PathVariable("postId") Long postId, @RequestBody @Valid UpdatePostRequestDto requestDto) {
        ResponseEntity<? super UpdatePostResponseDto> response = postService.updatePost(postId, requestDto);
        return response;
}
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<? super DeletePostResponseDto> deletePost(@PathVariable("postId") Long postId) {
        ResponseEntity<? super DeletePostResponseDto> response = postService.deletePost(postId);
        return response;
    }
}
