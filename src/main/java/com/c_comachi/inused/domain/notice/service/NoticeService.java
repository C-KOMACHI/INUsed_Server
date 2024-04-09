package com.c_comachi.inused.domain.notice.service;

import com.c_comachi.inused.domain.notice.dto.response.SearchNoticeResponseDto;
import com.c_comachi.inused.domain.notice.dto.response.ViewNoticeResponseDto;
import com.c_comachi.inused.domain.notice.entity.NoticeEntity;
import com.c_comachi.inused.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public ResponseEntity<? super SearchNoticeResponseDto> searchNotice(Long noticeId){
        NoticeEntity notice = null;

        try {
            if(!noticeRepository.existsById(noticeId)) return SearchNoticeResponseDto.notExistedNotice();
            notice = noticeRepository.findById(noticeId).get();
        } catch (Exception e){
            e.printStackTrace();
        }
        return SearchNoticeResponseDto.success(notice);
    }

    public ResponseEntity<? super ViewNoticeResponseDto> getNotices(){
        List<NoticeEntity> notices = noticeRepository.findAllByOrderByCreatedAtDesc();
        return ViewNoticeResponseDto.success(notices);
    }


}
