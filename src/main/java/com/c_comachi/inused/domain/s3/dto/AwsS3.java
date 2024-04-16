package com.c_comachi.inused.domain.s3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AwsS3 {
    private String key;
    private String path;


    @Builder
    public AwsS3(String key, String path){
        this.key = key;
        this.path = path;
    }
}
