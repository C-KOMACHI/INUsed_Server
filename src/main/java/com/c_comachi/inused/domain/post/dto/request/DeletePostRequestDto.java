package com.c_comachi.inused.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeletePostRequestDto {
    private long id;

    public DeletePostRequestDto(long id){
        this.id = id;
    }

}
