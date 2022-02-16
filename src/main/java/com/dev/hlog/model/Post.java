package com.dev.hlog.model;
import lombok.Builder;
import lombok.Getter;
import javax.persistence.*;
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

    public Post() {

    }
}
