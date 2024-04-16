package com.c_comachi.inused.domain.s3.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class S3RemoveRequestDto {
    private String key;
}
