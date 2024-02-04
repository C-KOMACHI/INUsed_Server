package com.c_comachi.inused.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostListItem {
    private int postId;
    private String title;
    private String postImage;
    private String writeTime;
    private int price;
    private int interestCount;
    private String saleStatus;
}
