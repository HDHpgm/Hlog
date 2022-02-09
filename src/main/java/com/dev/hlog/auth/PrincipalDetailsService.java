package com.dev.hlog.auth;


import com.dev.hlog.model.User;
import com.dev.hlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login-proc
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username : " + username);
        User userEntity = userRepository.findByUsername(username);

        if (userEntity != null) {
            return new PrincipalDetails(userEntity);
        }

        return null;
    }
}
