package com.dev.hlog.controller;

import com.dev.hlog.model.Post;
import com.dev.hlog.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;


class PostWriteTest {

    @Test
    public void postWrite() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        User newUser = User.builder()
                .username("test")
                .password("123")
                .role("ROLE_ADMIN")
                .build();
        set.add("태그1");
        set.add("태그2");
        final Post post = Post.builder()
                .user(newUser)
                .title("제목")
                .contentHtml("<h1>테스트</h1>")
                .tags(set)
                .build();

        String title = post.getTitle();
        System.out.println(post.getTags());
        System.out.println(post.getUser().getUsername());
        Assertions.assertEquals("제목", title);
    }
}