package com.c_comachi.inused.domain.s3.controller;

import com.c_comachi.inused.domain.s3.dto.AwsS3;
import com.c_comachi.inused.domain.s3.dto.request.S3RemoveRequestDto;
import com.c_comachi.inused.domain.s3.dto.response.S3UploadResponseDto;
import com.c_comachi.inused.domain.s3.service.AwsS3Service;
import com.c_comachi.inused.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "S3", description = "S3 관련 api 모음")
@RestController
@RequestMapping("api/v1/s3")
@RequiredArgsConstructor
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping("/resource")
    @Operation(summary = "사진 업로드")
    public ResponseEntity<? super S3UploadResponseDto> upload(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        AwsS3 awsS3 = awsS3Service.upload(multipartFile, "upload");
        return S3UploadResponseDto.success(awsS3);
    }

    @DeleteMapping("/resource")
    @Operation(summary = "사진 삭제")
    public ResponseEntity<ResponseDto> remove(@RequestBody S3RemoveRequestDto requestDto){
        awsS3Service.remove(requestDto);
        return ResponseDto.suc();
    }
}
