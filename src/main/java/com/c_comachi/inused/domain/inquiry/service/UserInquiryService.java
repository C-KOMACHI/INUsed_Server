package com.c_comachi.inused.domain.inquiry.service;

import com.c_comachi.inused.domain.inquiry.dto.ManagerInquiryInfo;
import com.c_comachi.inused.domain.inquiry.dto.UserInquiryEditInfo;
import com.c_comachi.inused.domain.inquiry.dto.request.CreateUserInquiryRequestDto;
import com.c_comachi.inused.domain.inquiry.dto.response.GetAllUserInquiryResponseDto;
import com.c_comachi.inused.domain.inquiry.dto.response.GetUserInquiryResponseDto;
import com.c_comachi.inused.domain.inquiry.entity.ManagerInquiryEntity;
import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import com.c_comachi.inused.domain.inquiry.repository.ManagerInquiryRepository;
import com.c_comachi.inused.domain.inquiry.repository.UserInquiryRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInquiryService {

    private final UserRepository userRepository;
    private final UserInquiryRepository userInquiryRepository;
    private final ManagerInquiryRepository managerInquiryRepository;


    public void createInquiry(CreateUserInquiryRequestDto createUserInquiryRequestDto, String email){
        UserInquiryEntity userInquiryEntity = null;
        try{
            userInquiryEntity = createUserInquiryRequestDto.toUserInquiry(userRepository, email);
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

    public ResponseEntity<? super GetUserInquiryResponseDto> getUserInquiry(Long userInquiryId){
        UserInquiryEntity userInquiry = null;
        ManagerInquiryInfo managerInquiryInfo = null;

        try{
            userInquiry = userInquiryRepository.findById(userInquiryId).get();

            if(managerInquiryRepository.existsByUserInquiryId(userInquiry)){
                ManagerInquiryEntity managerInquiry = managerInquiryRepository.findByUserInquiryId(userInquiry).get();
                managerInquiryInfo = new ManagerInquiryInfo(managerInquiry);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserInquiryResponseDto.success(userInquiry, managerInquiryInfo);
    }


    public void deleteInquiry(Long userInquiryId){
        userInquiryRepository.deleteById(userInquiryId);
    }

    public void editInquiry(Long userInquiryId, UserInquiryEditInfo info) {
        UserInquiryEntity userInquiry = userInquiryRepository.findById(userInquiryId).get();
        userInquiry.userInquiryEdit(info);
        userInquiryRepository.save(userInquiry);
    }
}
