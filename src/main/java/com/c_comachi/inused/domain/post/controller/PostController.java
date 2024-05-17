package com.c_comachi.inused.domain.post.controller;

import com.c_comachi.inused.domain.post.dto.request.CreatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.request.UpdatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.response.*;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
        return postService.createPost(user.getUsername(), requestsDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetPostResponseDto.class))),
    })
    @Operation(summary = "게시글 조회(단건 조회)")
    @GetMapping("/{postId}")
    public ResponseEntity<? super GetPostResponseDto> getPost(@PathVariable("postId") Long postId, @AuthenticationPrincipal UserDetails user) {
        return postService.getPost(postId, user);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(implementation = UpdatePostResponseDto.class))),
    })
    @Operation(summary = "게시글 수정")
    @PatchMapping("/{postId}/update")
    public ResponseEntity<? super UpdatePostResponseDto> updatePost(@PathVariable("postId") Long postId, @RequestBody @Valid UpdatePostRequestDto requestDto) {
        return postService.updatePost(postId, requestDto);
}

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable("postId") Long postId) {
        return postService.deletePost(postId);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "끌올 성공", content = @Content(schema = @Schema(implementation = RePostResponseDto.class))),
    })
    @Operation(summary = "게시글 끌올")
    @PatchMapping("/{postId}/reposting")
    public ResponseEntity<? super RePostResponseDto> rePost(@PathVariable("postId") Long postId) {
        return postService.rePost(postId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetAllPostResponseDto.class))),
    })
    @Operation(summary = "게시물 조회(전체 조회)")
    @GetMapping("")
    public ResponseEntity<? super GetAllPostResponseDto> viewNotice(@AuthenticationPrincipal UserDetails user){
        return postService.getAllPost(user);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "검색 성공", content = @Content(schema = @Schema(implementation = GetAllPostResponseDto.class))),
    })
    @Operation(summary = "게시물 검색")
    @GetMapping("/search")
    public ResponseEntity<? super GetAllPostResponseDto> searchPost(@AuthenticationPrincipal UserDetails user, @RequestParam(value = "search") String search){
        return postService.searchPost(user, search);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetAllPostResponseDto.class))),
    })
    @Operation(summary = "내 게시물 보기")
    @GetMapping("/my-post")
    public ResponseEntity<? super GetAllPostResponseDto> getMyPost(@AuthenticationPrincipal UserDetails user){
        return postService.getMyPost(user);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetAllPostResponseDto.class))),
    })
    @Operation(summary = "카테고리별 게시글 보기")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<? super GetAllPostResponseDto> getCategoryPost(@AuthenticationPrincipal UserDetails user,
                                                                         @PathVariable(value = "categoryId") Long categoryId){
        return postService.getCategoryPost(user, categoryId);
    }
}
