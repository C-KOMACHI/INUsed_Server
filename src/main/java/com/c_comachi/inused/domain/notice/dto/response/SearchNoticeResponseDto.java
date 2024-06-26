package com.c_comachi.inused.domain.notice.dto.response;

import com.c_comachi.inused.domain.notice.entity.NoticeEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;

import java.time.LocalDate;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SearchNoticeResponseDto extends ResponseDto {

    private String title;
    private String content;
    private String image;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private SearchNoticeResponseDto(NoticeEntity noticeEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.content = noticeEntity.getContent();
        this.title = noticeEntity.getTitle();
        this.image = noticeEntity.getImage();
        this.createdAt = noticeEntity.getCreatedAt();
        this.updatedAt = noticeEntity.getUpdatedAt();
    }

    public static ResponseEntity<SearchNoticeResponseDto> success(NoticeEntity noticeEntity) {
        SearchNoticeResponseDto result = new SearchNoticeResponseDto(noticeEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedNotice() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_NOTICE, ResponseMessage.NOT_EXISTED_NOTICE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
