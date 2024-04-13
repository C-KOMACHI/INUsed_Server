package com.c_comachi.inused.domain.inquiry.dto.response;

import com.c_comachi.inused.domain.inquiry.dto.ManagerInquiryInfo;
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
    private ManagerInquiryInfo managerInquiryInfo;

    private GetUserInquiryResponseDto(UserInquiryEntity userInquiry, ManagerInquiryInfo managerInquiryInfo) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.title = userInquiry.getTitle();
        this.content = userInquiry.getContent();
        this.createdAt = userInquiry.getCreatedAt();
        this.nickname = userInquiry.getUser().getNickname();
        this.managerInquiryInfo = managerInquiryInfo;
    }

    public static ResponseEntity<GetUserInquiryResponseDto> success(UserInquiryEntity userInquiry, ManagerInquiryInfo managerInquiryInfo){
        GetUserInquiryResponseDto result = new GetUserInquiryResponseDto(userInquiry, managerInquiryInfo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
