package com.c_comachi.inused.domain.users.dto.request;

import com.c_comachi.inused.domain.users.dto.UserEditInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserEditRequestDto {

    String profileImage;
    @NotBlank
    String nickname;

    public UserEditInfo toUserEditInfo() {
        return UserEditInfo.builder()
                .profileImage(profileImage)
                .nickname(nickname)
                .build();
    }
}
