package com.c_comachi.inused.domain.keyword.dto.request;

import com.c_comachi.inused.domain.keyword.entity.KeywordEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateKeywordRequestDto {
    private String keyword;

    public CreateKeywordRequestDto(String keyword) {
        this.keyword = keyword;
    }

    public KeywordEntity toKeywordEntity(UserEntity user) {
        return  KeywordEntity.builder()
                .name(keyword)
                .user(user)
                .build();
    }
}
