package com.dev.hlog.dto;

import com.dev.hlog.model.Post;
import com.dev.hlog.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@NoArgsConstructor
public class PostResponseDto {
    private long id;
    private String createDate;
    private String updateDate;
    private User user;
    private String title;
    private String contentHtml;
    private Set<String> tags;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.updateDate = post.getUpdateDate();
        this.user = post.getUser();
        this.title = post.getTitle();
        this.contentHtml = post.getContentHtml();
        this.tags = post.getTags();
    }



}
