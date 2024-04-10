package com.c_comachi.inused.domain.inquiry.dto.response;

import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetUserInquiryResponseDto extends ResponseDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String nickname;

    private GetUserInquiryResponseDto(UserInquiryEntity userInquiry) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.title = userInquiry.getTitle();
        this.content = userInquiry.getContent();
        this.createdAt = userInquiry.getCreatedAt();
        this.nickname = userInquiry.getUser().getNickname();
    }

    public static ResponseEntity<GetUserInquiryResponseDto> success(UserInquiryEntity userInquiry){
        GetUserInquiryResponseDto result = new GetUserInquiryResponseDto(userInquiry);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
