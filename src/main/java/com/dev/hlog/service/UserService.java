package com.dev.hlog.service;

import com.dev.hlog.dto.SignUpRequestDto;
import com.dev.hlog.dto.SignUpResponseDto;
import com.dev.hlog.model.User;
import com.dev.hlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SignUpResponseDto joinUser(SignUpRequestDto signUpRequestDto) {
        try {
            String username = signUpRequestDto.getUsername();
            String encodePassword = passwordEncoder.encode(signUpRequestDto.getPassword());
            String role = "ROLE_ADMIN";
            User user = User.builder()
                    .username(username)
                    .password(encodePassword)
                    .role(role)
                    .build();

            userRepository.save(user);
            SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
            signUpResponseDto.setUsername(user.getUsername());
            return signUpResponseDto;
        }
        catch (IllegalArgumentException e) { // save 메서드 예외발생 시 IllegalArgumentException
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
