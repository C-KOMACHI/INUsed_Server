package com.c_comachi.inused.domain.post.controller;

import com.c_comachi.inused.domain.notice.dto.response.SearchNoticeResponseDto;
import com.c_comachi.inused.domain.notice.dto.response.ViewNoticeResponseDto;
import com.c_comachi.inused.domain.post.dto.request.CreatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.request.UpdatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.response.*;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.post.service.PostService;
import com.c_comachi.inused.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Tag(name = "Post" , description = "Post 관련 API 모음")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(schema = @Schema(implementation = CreatePostResponseDto.class))),
    })
    @Operation(summary = "게시글 생성")
    @PostMapping("/create")
    public ResponseEntity<? super CreatePostResponseDto> createPost(@RequestBody @Valid CreatePostRequestDto requestsDto, @AuthenticationPrincipal UserDetails user) {
        ResponseEntity<? super CreatePostResponseDto> response = postService.createPost(user.getUsername(), requestsDto);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetPostResponseDto.class))),
    })
    @Operation(summary = "게시글 조회(단건 조회)")
    @GetMapping("/{postId}")
    public ResponseEntity<? super GetPostResponseDto> getPost(@PathVariable("postId") Long postId) {
        ResponseEntity<? super GetPostResponseDto> response = postService.getPost(postId);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(implementation = UpdatePostResponseDto.class))),
    })
    @Operation(summary = "게시글 수정")
    @PatchMapping("/{postId}/update")
    public ResponseEntity<? super UpdatePostResponseDto> updatePost(@PathVariable("postId") Long postId, @RequestBody @Valid UpdatePostRequestDto requestDto) {
        ResponseEntity<? super UpdatePostResponseDto> response = postService.updatePost(postId, requestDto);
        return response;
}

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(schema = @Schema(implementation = DeletePostResponseDto.class))),
    })
    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<? super DeletePostResponseDto> deletePost(@PathVariable("postId") Long postId) {
        ResponseEntity<? super DeletePostResponseDto> response = postService.deletePost(postId);
        return response;
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "끌올 성공", content = @Content(schema = @Schema(implementation = RePostResponseDto.class))),
    })
    @Operation(summary = "게시글 끌올")
    @PatchMapping("/{postId}/reposting")
    public ResponseEntity<? super RePostResponseDto> rePost(@PathVariable("postId") Long postId) {
        ResponseEntity<? super RePostResponseDto> response = postService.rePost(postId);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = AllGetPostResponseDto.class))),
    })
    @Operation(summary = "게시물 조회(전체 조회)")
    @GetMapping("")
    public ResponseEntity<? super AllGetPostResponseDto> viewNotice(@AuthenticationPrincipal UserDetails user){
        ResponseEntity<? super AllGetPostResponseDto> response = postService.getAllPost(user);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "검색 성공", content = @Content(schema = @Schema(implementation = AllGetPostResponseDto.class))),
    })
    @Operation(summary = "게시물 검색")
    @GetMapping("/search")
    public ResponseEntity<? super AllGetPostResponseDto> searchPost(@AuthenticationPrincipal UserDetails user,@RequestParam(value = "search") String search){
        ResponseEntity<? super AllGetPostResponseDto> response = postService.searchPost(user, search);
        return response;
    }
}
