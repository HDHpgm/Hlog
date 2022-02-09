package com.dev.hlog.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import java.util.Set;


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

    public Post() {

    }
}
