package com.dev.hlog.service;

import com.dev.hlog.auth.PrincipalDetails;
import com.dev.hlog.dto.PostResponseDto;
import com.dev.hlog.dto.PostWriteRequestDto;
import com.dev.hlog.model.ErrorCode;
import com.dev.hlog.model.Post;

import com.dev.hlog.repository.PostRepository;

import com.dev.hlog.util.exeption.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 포스트 작성 함수
    public void postWrite(PostWriteRequestDto postWriteRequestDto, PrincipalDetails principalDetails) {
        if (principalDetails == null) {
            System.out.println("포스트 게시중에 예외 발생");
            throw new UserException("", ErrorCode.USER_NULL);
        }

        if (postWriteRequestDto.getTitle().equals("")) {
            System.out.println("포스트 게시중에 예외 발생");
            throw new UserException("" ,ErrorCode.POST_TITLE_NULL);
        }
        else if (postWriteRequestDto.getContentHtml().equals("")) {
            System.out.println("포스트 게시중에 예외 발생");
            throw new UserException("" ,ErrorCode.POST_CONTENT_NULL);
        }

        try {

            Post newPost = Post.builder()
                    .user(principalDetails.getUser())
                    .title(postWriteRequestDto.getTitle())
                    .contentHtml(postWriteRequestDto.getContentHtml())
                    .tags(postWriteRequestDto.getTags())
                    .build();

            postRepository.save(newPost);

        }
        catch (IllegalArgumentException e) { // save 메서드 예외발생 시 IllegalArgumentException
            e.printStackTrace();
            throw e;
        }
    }

    // 모든 포스트 반환 함수
    public List<PostResponseDto> findAllPost() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));

        // posts 의 각 요소들을 PostResponseDto 로 매핑해서 List 로 반환
        List<PostResponseDto> postsResponse = posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
        System.out.println(postsResponse);
        return postsResponse;
    }

    // 각 포스트를 보여주기 위한 서비스
    public PostResponseDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new UserException("", ErrorCode.POST_IS_NULL));
        return new PostResponseDto(post);
    }

    // 포스트 삭제 서비스
    public String postDeleteById(Long id) {
        try {
            postRepository.deleteById(id);
        }
        catch (IllegalArgumentException e) {
            throw new UserException("", ErrorCode.POST_DELETE_FAIL);
        }
        return "삭제완료";
    }
}
