package com.c_comachi.inused.domain.inquiry.dto.request;

import com.c_comachi.inused.domain.inquiry.dto.UserInquiryEditInfo;
import com.c_comachi.inused.domain.users.dto.UserEditInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditUserInquiryRequestDto {
    private String title;
    private String content;

    public UserInquiryEditInfo toUserInquiryEditInfo() {
        return UserInquiryEditInfo.builder()
                .title(title)
                .content(content)
                .build();
    }
}
