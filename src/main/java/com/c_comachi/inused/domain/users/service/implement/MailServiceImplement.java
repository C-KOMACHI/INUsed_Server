package com.c_comachi.inused.domain.users.service.implement;

import com.c_comachi.inused.domain.users.dto.response.EmailCheckResponseDto;
import com.c_comachi.inused.domain.users.dto.response.RegisterResponseDto;
import com.c_comachi.inused.domain.users.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class MailServiceImplement implements MailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private String authCode;

    @Value("${spring.mail.username}")
    private String senderEmail;

    private final String emailAddress = "@inu.ac.kr";

//    @Override
//    public void sendMail(Long id, String email) {
//        try {
//            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//            mimeMessageHelper.setSubject("[INUsed 이메일 인증]");
//            mimeMessageHelper.setFrom(senderEmail);
//            mimeMessageHelper.setTo(email);
//
//            Context context = new Context();
//            context.setVariable("id", id);
//
//            String html = templateEngine.process("emailTemplate", context);
//            mimeMessageHelper.setText(html, true);
//
//            javaMailSender.send(mimeMailMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0; i<8; i++) {
            int idx = random.nextInt(3);

            switch (idx) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authCode = key.toString();
    }

    @Override
    public MimeMessage createEmailForm(String email) {
        createCode();
        String toEmail = email + emailAddress;
        String title = "[INUsed] 회원가입 인증 코드";

        MimeMessage message = javaMailSender.createMimeMessage();
        try{

            message.addRecipients(MimeMessage.RecipientType.TO, toEmail);
            message.setSubject(title);

            // 메일 내용 HTML
            String msgOfEmail = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; border-radius: 10px;'>";
            msgOfEmail += "<div style='text-align: center; margin-bottom: 20px;'>";
            msgOfEmail += "<img src='https://private-user-images.githubusercontent.com/79833638/294596482-792baf8a-209e-4307-aee8-094635c09ed1.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MDczMDY0MTksIm5iZiI6MTcwNzMwNjExOSwicGF0aCI6Ii83OTgzMzYzOC8yOTQ1OTY0ODItNzkyYmFmOGEtMjA5ZS00MzA3LWFlZTgtMDk0NjM1YzA5ZWQxLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDAyMDclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwMjA3VDExNDE1OVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTZiMmVjYWNjNTZjMzYzYmNlMDQ4YTBlNWFjMzk1MjY4ZjViMmQwODg4NjcyNjkyYjM1OWQ2YmE0YjNiZDZmMDQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.FzTrZwSMpkugxjlSDefb5cA_GQVA99IyGLdLWv89bsQ' alt='INUsed Logo' style='width: 150px;'>";
            msgOfEmail += "<h2 style='color: #007bff;'>회원가입 인증 코드</h2>";
            msgOfEmail += "</div>";
            msgOfEmail += "<div style='background-color: #ffffff; padding: 30px; border-radius: 10px;'>";
            msgOfEmail += "<p style='font-size: 16px; text-align: center; color: #333333;'>안녕하세요, INUsed에 오신 것을 환영합니다!</p>";
            msgOfEmail += "<br>";
            msgOfEmail += "<p style='font-size: 16px; text-align: center; color: #333333;'>아래 인증코드를 입력해주세요.</p>";
            msgOfEmail += "<div style='text-align: center; margin-top: 30px;'>";
            msgOfEmail += "<div style='background-color: #007bff; color: #ffffff; padding: 15px 30px; border-radius: 5px; display: inline-block;'>";
            msgOfEmail += "<h1 style='font-size: 36px; margin: 0;'>" + authCode + "</h1>";
            msgOfEmail += "</div>";
            msgOfEmail += "</div>";
            msgOfEmail += "<p style='font-size: 16px; text-align: center; margin-top: 30px; color: #333333;'>감사합니다.</p>";
            msgOfEmail += "</div>";
            msgOfEmail += "</div>";

            message.setFrom(senderEmail);
            message.setText(msgOfEmail, "utf-8", "html");
        } catch (Exception e){
            e.printStackTrace();
        }

        return message;
    }

    @Override
    public ResponseEntity<? super EmailCheckResponseDto> sendEmail(String email) throws MessagingException, UnsupportedEncodingException {

        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(email);
        //실제 메일 전송
        javaMailSender.send(emailForm);

        return EmailCheckResponseDto.success(authCode);
    }

    @Override
    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException e) {
            result = false;
        }

        return result;
    }
}
