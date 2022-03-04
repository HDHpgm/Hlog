package com.dev.hlog.model;
import com.dev.hlog.dto.PostWriteRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
public class Post extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Lob
    private String contentHtml;

    @ElementCollection
    private Set<String> tags;

    @Builder
    public Post(User user, String title, String contentHtml, LinkedHashSet<String> tags) {
        this.user = user;
        this.title = title;
        this.contentHtml = contentHtml;
        this.tags = tags;
    }

    public void update(PostWriteRequestDto postUpdateDto) {
        this.title = postUpdateDto.getTitle();
        this.contentHtml = postUpdateDto.getContentHtml();
        this.tags = postUpdateDto.getTags();
    }

}
