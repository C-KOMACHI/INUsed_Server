package com.c_comachi.inused.domain.firebase.controller;

import com.c_comachi.inused.domain.firebase.dto.request.FirebaseNotificationRequestDto;
import com.c_comachi.inused.domain.firebase.dto.response.FirebaseNotificationResponseDto;
import com.c_comachi.inused.domain.firebase.service.FirebaseNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "FCMNotification" , description = "Firebase Notification API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/FCMNotification")

public class FirebaseNotificationController {

    private FirebaseNotificationService firebaseNotificationService;

    @Autowired
    public void setFirebaseNotificationService(FirebaseNotificationService firebaseNotificationService) {
        this.firebaseNotificationService = firebaseNotificationService;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "전송 성공") //content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "알림 전송")
    @PostMapping("/send")
//    public String sendNotification(@RequestBody FirebaseNotificationRequestDto requestDto){
//        return firebaseNotificationService.sendNotification(requestDto);
//    }

    public ResponseEntity<? super FirebaseNotificationResponseDto> sendNotification(@RequestBody @Valid FirebaseNotificationRequestDto requestDto) {
        try {
            return firebaseNotificationService.sendNotification(requestDto);
        } catch (Exception e) {
            e.printStackTrace();
            return FirebaseNotificationResponseDto.failure();
        }
    }
}
