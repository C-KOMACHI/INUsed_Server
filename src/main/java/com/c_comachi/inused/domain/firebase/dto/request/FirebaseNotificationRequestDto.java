package com.c_comachi.inused.domain.firebase.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FirebaseNotificationRequestDto {

    private String token;

    private String title;

    private String body;

    private String image;

    @Builder
    public FirebaseNotificationRequestDto(String token, String title, String body, String image) {
        this.token = token;
        this.title = title;
        this.body = body;
        this.image = image;
    }
}
