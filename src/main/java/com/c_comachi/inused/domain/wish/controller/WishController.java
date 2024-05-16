package com.c_comachi.inused.domain.wish.controller;

import com.c_comachi.inused.domain.post.dto.request.CreatePostRequestDto;
import com.c_comachi.inused.domain.post.dto.response.CreatePostResponseDto;
import com.c_comachi.inused.domain.post.dto.response.DeletePostResponseDto;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.wish.dto.request.CreateWishRequestDto;
import com.c_comachi.inused.domain.wish.dto.response.CreateWishResponseDto;
import com.c_comachi.inused.domain.wish.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Wish" , description = "Wish 관련 API 모음")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/wishes")
public class WishController {
    @Autowired
    private WishService wishService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "관심등록 성공", content = @Content(schema = @Schema(implementation = CreatePostResponseDto.class))),
    })
    @Operation(summary = "관심 등록")
    @PostMapping("/{postId}/add") //@RequestBody
    public ResponseEntity<? super CreateWishResponseDto> addWish(@Valid CreateWishRequestDto requestsDto,UserEntity user, PostEntity post) {
        ResponseEntity<? super CreateWishResponseDto> response = wishService.addWish(requestsDto, user, post);
        return response;
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "관심해제 성공", content = @Content(schema = @Schema(implementation = CreatePostResponseDto.class))),
    })
    @Operation(summary = "관심 해제")
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<?> deleteWish(@PathVariable Long postId, Authentication authentication,PostEntity post) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        wishService.deleteWish(post, user);
        return ResponseEntity.ok("Wish removed success.");
    }
//    public ResponseEntity<? super DeletePostResponseDto> deletePost(@PathVariable("postId") Long postId) {
//        ResponseEntity<? super DeletePostResponseDto> response = postService.deletePost(postId);
//        return response;
//    }
}
