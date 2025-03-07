package com.c_comachi.inused.domain.review.service;

import com.c_comachi.inused.domain.chat.entity.ChatRoom;
import com.c_comachi.inused.domain.chat.repository.ChatRoomRepository;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.review.dto.request.CreateReviewRequestDto;
import com.c_comachi.inused.domain.review.dto.response.StartReviewResponseDto;
import com.c_comachi.inused.domain.review.entity.ReviewEntity;
import com.c_comachi.inused.domain.review.repository.ReviewRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.global.common.ErrorCode;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public void createReview(CreateReviewRequestDto requestDto, UserDetails user){
        UserEntity sender = userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        UserEntity receiver = userRepository.findById(requestDto.getReceiverId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        PostEntity post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));

        receiver.setFireTemperature( receiver.getFireTemperature() + calculateManner(requestDto));
        ReviewEntity review = requestDto.toReviewEntity(post,receiver,sender);

        userRepository.save(receiver);
        reviewRepository.save(review);
    }

    @Transactional
    public ResponseEntity<? super StartReviewResponseDto> startReview(Long postId, UserDetails user){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByReceiverEmailAndPost(user.getUsername(), post);

        return StartReviewResponseDto.success(chatRooms, post.getTitle(), post.getImageUrl());
    }

    private Integer calculateManner(CreateReviewRequestDto requestDto){
        int sum = requestDto.getManner() + requestDto.getAppointment_time() + requestDto.getQuality();
        return (sum - 9) * 2;
    }
}
