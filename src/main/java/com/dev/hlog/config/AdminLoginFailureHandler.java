package com.dev.hlog.config;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class AdminLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg = "관리자 로그인 정보가 일치하지 않습니다.";

        if (exception instanceof AuthenticationServiceException) {
            msg = "존재하지 않는 사용자입니다.";

        } else if(exception instanceof BadCredentialsException) {
            msg = "아이디 또는 비밀번호가 틀립니다.";

        } else if(exception instanceof LockedException) {
           msg = "잠긴 계정입니다.";

        } else if(exception instanceof DisabledException) {
            msg = "비활성화된 계정입니다.";

        } else if(exception instanceof AccountExpiredException) {
            msg = "만료된 계정입니다.";

        } else if(exception instanceof CredentialsExpiredException) {
            msg = "비밀번호가 만료되었습니다.";
        }
        System.out.println(msg);
        
        // 한글로 인코딩
        msg = URLEncoder.encode(msg, "UTF-8");
        setDefaultFailureUrl("/admin/login?error=true&exception="+msg);

        super.onAuthenticationFailure(request,response,exception);
    }
}
