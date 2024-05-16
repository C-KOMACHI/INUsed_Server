package com.c_comachi.inused.domain.chat.dto.response;

import com.c_comachi.inused.domain.chat.entity.ChatRoom;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ViewChatRoomResponseDto extends ResponseDto {
    private ChatRoom chatRoom;

    private ViewChatRoomResponseDto(ChatRoom chatRoom){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRoom = chatRoom;
    }

    public static ResponseEntity<? super ViewChatRoomResponseDto> success(ChatRoom chatRoom){
        ViewChatRoomResponseDto result = new ViewChatRoomResponseDto(chatRoom);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
