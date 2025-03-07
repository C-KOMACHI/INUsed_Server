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
import com.c_comachi.inused.global.common.ErrorCode;
import com.c_comachi.inused.global.dto.ResponseDto;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInquiryService {

    private final UserRepository userRepository;
    private final UserInquiryRepository userInquiryRepository;
    private final ManagerInquiryRepository managerInquiryRepository;


    @Transactional
    public void createInquiry(CreateUserInquiryRequestDto createUserInquiryRequestDto, String email){
        UserInquiryEntity userInquiryEntity = null;
        try{
            userInquiryEntity = createUserInquiryRequestDto.toUserInquiry(userRepository, email);
            userInquiryRepository.save(userInquiryEntity);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public ResponseEntity<? super GetAllUserInquiryResponseDto> getAllUserInquiry(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        List<UserInquiryEntity> userInquiryList = userInquiryRepository.findListByUser(user);

        return GetAllUserInquiryResponseDto.success(userInquiryList);
    }

    @Transactional
    public ResponseEntity<? super GetUserInquiryResponseDto> getUserInquiry(Long userInquiryId, String email){
        UserInquiryEntity userInquiry = null;
        ManagerInquiryInfo managerInquiryInfo = null;

        try{
            if(!userInquiryRepository.existsById(userInquiryId)) return GetUserInquiryResponseDto.notExistedInquiry();
            userInquiry = userInquiryRepository.findById(userInquiryId)
                    .orElseThrow(() -> new EntityNotFoundException(ErrorCode.INQUIRY_NOT_FOUND));

            String userEmail = userInquiry.getUser().getEmail();

            if(!userEmail.equals(email)) return GetUserInquiryResponseDto.authorizationFailed();

            if(managerInquiryRepository.existsByUserInquiryId(userInquiry)){
                ManagerInquiryEntity managerInquiry = managerInquiryRepository.findByUserInquiryId(userInquiry)
                        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.INQUIRY_NOT_FOUND));

                managerInquiryInfo = new ManagerInquiryInfo(managerInquiry);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserInquiryResponseDto.success(userInquiry, managerInquiryInfo);
    }

    @Transactional
    public void deleteInquiry(Long userInquiryId){
        userInquiryRepository.deleteById(userInquiryId);
    }

    @Transactional
    public void editInquiry(Long userInquiryId, UserInquiryEditInfo info) {
        UserInquiryEntity userInquiry = userInquiryRepository.findById(userInquiryId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.INQUIRY_NOT_FOUND));

        userInquiry.userInquiryEdit(info);
        userInquiryRepository.save(userInquiry);
    }
}
