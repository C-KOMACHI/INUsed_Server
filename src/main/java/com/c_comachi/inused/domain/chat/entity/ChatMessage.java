package com.c_comachi.inused.domain.chat.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.lang.annotation.Documented;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "chatMessage")
@NoArgsConstructor
public class ChatMessage {

    // 메시지 타입 : 입장, 채팅

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private Long roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private String createdTime;

    @Builder
    public ChatMessage(String message, Long roomId, String sender){
        this.message = message;
        this.sender = sender;
        this.roomId = roomId;
        this.createdTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

}
