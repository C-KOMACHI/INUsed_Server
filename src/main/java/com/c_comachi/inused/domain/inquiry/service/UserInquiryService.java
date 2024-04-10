package com.c_comachi.inused.domain.inquiry.service;

import com.c_comachi.inused.domain.inquiry.dto.request.CreateInquiryRequestDto;
import com.c_comachi.inused.domain.inquiry.dto.response.GetAllUserInquiryResponseDto;
import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import com.c_comachi.inused.domain.inquiry.repository.UserInquiryRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInquiryService {

    private final UserRepository userRepository;
    private final UserInquiryRepository userInquiryRepository;


    public void createInquiry(CreateInquiryRequestDto createInquiryRequestDto, String email){
        UserInquiryEntity userInquiryEntity = null;
        try{
            userInquiryEntity = createInquiryRequestDto.toUserInquiry(userRepository, email);

            userInquiryRepository.save(userInquiryEntity);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResponseEntity<? super GetAllUserInquiryResponseDto> getAllUserInquiry(String email) {
        UserEntity user = userRepository.findByEmail(email).get();
        List<UserInquiryEntity> userInquiryList = userInquiryRepository.findListByUser(user);

        return GetAllUserInquiryResponseDto.success(userInquiryList);
    }


    public void deleteInquiry(Long userInquiryId){
        userInquiryRepository.deleteById(userInquiryId);
    }
}
