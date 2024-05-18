package com.c_comachi.inused.domain.review.dto.response;

import com.c_comachi.inused.domain.chat.entity.ChatRoom;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class StartReviewResponseDto extends ResponseDto {

    private final List<ChatRoom> chatRooms;
    private final String postTitle;
    private final String postImageUrl;

    public StartReviewResponseDto(List<ChatRoom> chatRooms, String postTitle, String postImageUrl) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRooms = chatRooms;
        this.postTitle = postTitle;
        this.postImageUrl = postImageUrl;
    }

    public static ResponseEntity<StartReviewResponseDto> success(List<ChatRoom> chatRooms, String postTitle, String postImageUrl){
        StartReviewResponseDto result = new StartReviewResponseDto(chatRooms, postTitle, postImageUrl);
        return ResponseEntity.ok(result);
    }
}
