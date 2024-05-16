package com.c_comachi.inused.domain.wish.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteWishRequestDto {
    private long id;

    public DeleteWishRequestDto(long id){
        this.id = id;
    }
}
