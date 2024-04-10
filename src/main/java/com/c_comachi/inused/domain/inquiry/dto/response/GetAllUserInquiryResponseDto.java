package com.c_comachi.inused.domain.inquiry.dto.response;

import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import com.c_comachi.inused.domain.users.dto.response.LogoutResponseDto;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetAllUserInquiryResponseDto extends ResponseDto {

    private List<UserInquiryEntity> userInquiryList;

    private GetAllUserInquiryResponseDto(List<UserInquiryEntity> userInquiryList){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userInquiryList = userInquiryList;
    }

    public static ResponseEntity<GetAllUserInquiryResponseDto> success(List<UserInquiryEntity> userInquiryList){
        GetAllUserInquiryResponseDto result = new GetAllUserInquiryResponseDto(userInquiryList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }




}
