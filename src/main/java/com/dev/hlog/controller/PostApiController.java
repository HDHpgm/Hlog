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

    @DeleteMapping("/post/delete/{id}")
    public String postDeleteById(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails.getUsername().equals("admin")) {
           return postService.postDeleteById(id);
        }
        return "삭제 오류";
    }

    @PutMapping("/post/update/{id}")
    public String postUpdateById(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails,
                             @RequestBody PostWriteRequestDto postUpdateDto)
    {
        if (principalDetails.getUsername().equals("admin")) {
            return postService.postUpdateById(id, postUpdateDto);
        }
        return "수정 오류";
    }



}
