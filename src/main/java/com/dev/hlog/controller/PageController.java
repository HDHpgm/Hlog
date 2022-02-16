package com.dev.hlog.controller;



import com.dev.hlog.auth.PrincipalDetails;
import com.dev.hlog.dto.PostResponseDto;
import com.dev.hlog.model.Post;
import com.dev.hlog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final PostService postService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        addAttr(principalDetails, model);
        return "index";
    }

    @GetMapping("/about")
    public String about(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        addAttr(principalDetails, model);
        return "about";
    }

    @GetMapping("markdown")
    public String markdown() {
        return "markdown";
    }

    @GetMapping("/admin/login")
    public String admin(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model)
    {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "admin-login";
    }

    @GetMapping("/post/detail/{id}")
    public String postDetail(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model, @PathVariable Long id) {
        PostResponseDto postResponseDto = postService.findPostById(id);
        model.addAttribute("post", postResponseDto);
        addAttr(principalDetails, model);
        return "post";
    }





    public void addAttr(PrincipalDetails principalDetails, Model model) {
        if (principalDetails != null)
            model.addAttribute("login", principalDetails.getUsername());
        else
            model.addAttribute("login", null);
    }
}
