package com.c_comachi.inused.domain.inquiry.dto.request;

import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateUserInquiryRequestDto {

    private String title;
    private String content;

    public CreateUserInquiryRequestDto(String title, String content){
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
