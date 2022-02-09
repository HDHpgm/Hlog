package com.dev.hlog.controller;


import com.dev.hlog.auth.PrincipalDetails;
import com.dev.hlog.dto.SignUpRequestDto;
import com.dev.hlog.dto.SignUpResponseDto;
import com.dev.hlog.model.User;
import com.dev.hlog.repository.UserRepository;
import com.dev.hlog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserRepository userRepository;

    private final UserService userService;


    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody SignUpRequestDto signUpRequestDto){
        SignUpResponseDto signUpResponseDto = userService.joinUser(signUpRequestDto);
        return signUpResponseDto.getUsername();
    }

    // user , admin 권한
    @GetMapping("/api/user")
    public String user(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication :" + principalDetails.getUsername());
        return "user";
    }

    // admin 권한
    @GetMapping("/api/admin")
    public String admin() {
        return "admin";
    }


}
