package com.c_comachi.inused.domain.firebase.service;

import com.c_comachi.inused.domain.firebase.dto.request.FirebaseNotificationRequestDto;
import com.c_comachi.inused.domain.firebase.dto.response.FirebaseNotificationResponseDto;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FirebaseNotificationService {
    @Transactional
    public ResponseEntity<? super FirebaseNotificationResponseDto> sendNotification(FirebaseNotificationRequestDto requestsDto) throws Exception{
        Notification.Builder notificationBuilder = Notification.builder()
                .setTitle(requestsDto.getTitle())
                .setBody(requestsDto.getBody());

        //이미지 존재할 시 알림에 추가
        if (requestsDto.getImage() != null && !requestsDto.getImage().isEmpty()) {
            notificationBuilder.setImage(requestsDto.getImage());
        }

        Notification notification = notificationBuilder.build();

        Message message = Message.builder()
                .setToken(requestsDto.getToken())
                .setNotification(notification)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
            return FirebaseNotificationResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return FirebaseNotificationResponseDto.failure();
        }
    }
//    public String sendNotification(FirebaseNotificationRequestDto requestDto) {
//        Notification.Builder notificationBuilder = Notification.builder()
//                .setTitle(requestDto.getTitle())
//                .setBody(requestDto.getBody());
//
//        //이미지 존재할 시 알림에 추가
//        if (requestDto.getImage() != null && !requestDto.getImage().isEmpty()) {
//            notificationBuilder.setImage(requestDto.getImage());
//        }
//
//        Notification notification = notificationBuilder.build();
//
//        Message message = Message.builder()
//                .setToken(requestDto.getToken())
//                .setNotification(notification)
//                .build();
//
//        try {
//            FirebaseMessaging.getInstance().send(message);
//            return "알림 전송 성공";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "알림 전송 실패";
//        }
//    }
}