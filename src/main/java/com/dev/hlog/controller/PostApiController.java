package com.dev.hlog.controller;

import com.dev.hlog.auth.PrincipalDetails;
import com.dev.hlog.dto.PostResponseDto;
import com.dev.hlog.dto.PostWriteRequestDto;
import com.dev.hlog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("/post/write")
    public String postWrite(@RequestBody PostWriteRequestDto postWriteRequestDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        postService.postWrite(postWriteRequestDto, principalDetails);
        return "작성완료";
    }

    @GetMapping("/post/find/all")
    public List<PostResponseDto> findAllPost() {
        return postService.findAllPost();
    }

    @GetMapping("/post/find")
    public PostResponseDto findPostById(@RequestParam Long id) {
        return postService.findPostById(id);
    }


}
