package com.c_comachi.inused.domain.users.service.implement;

import com.c_comachi.inused.domain.users.dto.response.EmailCheckResponseDto;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.domain.users.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class MailServiceImplement implements MailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    private String authCode;
    @Value("${spring.mail.username}")
    private String senderEmail;
    private final String EMAIL_ADDRESS = "@inu.ac.kr";
    private final int AUTH_CODE_LENGTH = 8;
    private final String TITLE = "[INUsed] 회원가입 인증 코드";

    @Override
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0; i<AUTH_CODE_LENGTH; i++) {
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
        String toEmail = email + EMAIL_ADDRESS;
        String title = TITLE;

        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            message.addRecipients(MimeMessage.RecipientType.TO, toEmail);
            message.setSubject(title);

            String msgOfEmail = createHtml();

            message.setFrom(senderEmail);
            message.setText(msgOfEmail, "utf-8", "html");
        } catch (Exception e){
            e.printStackTrace();
        }

        return message;
    }

    @Override
    public ResponseEntity<? super EmailCheckResponseDto> sendEmail(String email) throws MessagingException, UnsupportedEncodingException {

        // 이메일 존재하면 return 중복 이메일
        if(userRepository.existsByEmail(email+EMAIL_ADDRESS)){
            return EmailCheckResponseDto.duplicateEmail();
        }
        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(email);
        //실제 메일 전송
        javaMailSender.send(emailForm);

        return EmailCheckResponseDto.success(authCode);
    }

    public String createHtml(){
        String msgOfEmail = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; border-radius: 10px;'>";
        msgOfEmail += "<div style='text-align: center; margin-bottom: 20px;'>";
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

        return msgOfEmail;
    }
}
