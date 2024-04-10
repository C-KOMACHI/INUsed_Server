package com.c_comachi.inused.domain.inquiry.dto.request;

import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateInquiryRequestDto {

    private String title;
    private String content;

    public CreateInquiryRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }

    public UserInquiryEntity toUserInquiry(UserRepository userRepository, String email) {
        UserEntity user = userRepository.findByEmail(email).get();

        return UserInquiryEntity.builder()
                .user(user)
                .content(content)
                .title(title)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
