package com.c_comachi.inused.domain.s3.dto;

import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class S3UploadResponseDto extends ResponseDto {
    private String key;
    private String path;

    public S3UploadResponseDto (AwsS3 awsS3){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.key = awsS3.getKey();
        this.path = awsS3.getPath();
    }

    public static ResponseEntity<S3UploadResponseDto> success(AwsS3 awsS3){
        S3UploadResponseDto result = new S3UploadResponseDto(awsS3);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
