package com.dev.hlog.dto;

import lombok.Getter;

import java.util.LinkedHashSet;


@Getter
public class PostWriteRequestDto {
    private String title;
    private String contentHtml;
    private LinkedHashSet<String> tags;
}
