package com.c_comachi.inused.domain.post.dto.response;

import com.c_comachi.inused.domain.notice.dto.response.ViewNoticeResponseDto;
import com.c_comachi.inused.domain.notice.entity.NoticeEntity;
import com.c_comachi.inused.domain.post.dto.PostInfo;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
public class AllGetPostResponseDto extends ResponseDto {
    private List<PostInfo> posts;

    private AllGetPostResponseDto(List<PostInfo> posts){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.posts = posts;

    }

    public static ResponseEntity<? super AllGetPostResponseDto> success(List<PostInfo> posts){
        AllGetPostResponseDto result = new AllGetPostResponseDto(posts);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
