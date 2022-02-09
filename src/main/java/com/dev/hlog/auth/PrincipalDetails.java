package com.dev.hlog.auth;

import com.dev.hlog.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// security 가 /login-proc 주소요청이 오면 낚아채서 로그인 진행
// 로그인 진행 완료되면 session 을 만들어줄 것임 (시큐리티 ContextHolder)
// 세션에는 Authentication 타입 객체가 들어갈 수 있음
// Authentication 안에는 User 정보가 있어야 함
// User 오브젝트 타입도 정해져있음 => UserDetails 타입

// (Security Session => (Authentication => (UserDetails)))
@Data
public class PrincipalDetails implements UserDetails {
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoleList().forEach(r -> {
            authorities.add(() -> r);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
