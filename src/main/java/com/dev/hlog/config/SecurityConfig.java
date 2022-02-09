package com.dev.hlog.config;


import com.dev.hlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;
    private final AdminLoginFailureHandler adminLoginFailureHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http
                .addFilter(corsFilter) // @CrossOrigin(인증이 필요없을때사용) / 있을때는 시큐리티 필터에 등록

                .authorizeRequests()
                .antMatchers("/api/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/markdown")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()

                .formLogin()
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/login-proc")
                        .defaultSuccessUrl("/")
                        .failureUrl("/admin/login")
                        .failureHandler(adminLoginFailureHandler)
                        .permitAll()
                        .and()
                .logout()
                        .logoutSuccessUrl("/")
                        .permitAll();
    }
}
