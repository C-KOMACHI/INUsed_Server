package com.c_comachi.inused.domain.notice.service;

import com.c_comachi.inused.domain.notice.dto.response.SearchNoticeResponseDto;
import com.c_comachi.inused.domain.notice.entity.NoticeEntity;
import com.c_comachi.inused.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public ResponseEntity<? super SearchNoticeResponseDto> getNotice(Long noticeId){
        NoticeEntity notice = noticeRepository.findById(noticeId).orElseThrow();
        return SearchNoticeResponseDto.success(notice);
    }
}
