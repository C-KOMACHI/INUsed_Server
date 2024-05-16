package com.c_comachi.inused.domain.chat.dto.response;

import com.c_comachi.inused.domain.chat.entity.ChatRoom;
import com.c_comachi.inused.domain.notice.dto.response.ViewNoticeResponseDto;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ViewAllChatRoomResponseDto extends ResponseDto {
    private List<ChatRoom> chatRooms;

    private ViewAllChatRoomResponseDto(List<ChatRoom> chatRooms){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRooms = chatRooms;
    }

    public static ResponseEntity<? super ViewAllChatRoomResponseDto> success(List<ChatRoom> chatRooms){
        ViewAllChatRoomResponseDto result = new ViewAllChatRoomResponseDto(chatRooms);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
