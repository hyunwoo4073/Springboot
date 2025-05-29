package com.example.sbb;

import com.example.sbb.user.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

// 스프링 시큐리티 설정
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserController userController;

    SecurityConfig(UserController userController) {
        this.userController = userController;
    }
    // 인증되지 않은 모든 페이지의 요청을 허락
    // 로그인하지 않더라도 모든 페이지 접근 가능
    // CSRF는 웹 보안 공격 중 하나로 조작된 정보로 웹 사이트가 실행되도록 속이는 공격 기술
    // 스프링 시큐리티는 이러한 공격을 방지하기 위해 CSRF 토큰을 세션을 통해 발행
    // 웹 페이지에서는 폼 전송 시에 해당 토큰을 함께 전송하여 실제 웹 페이지에서 작성한 데이터가 전달되는지 검증
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
            .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
            .headers((headers) -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
            )))
            // 검증 제외 처리, H2 콘솔은 프레임 구조로 작성되어 있음, X-Frame-Options 헤더 기본값은 DENY
            // 스프링 부트에서 X-Frame-Options 헤더는 클릭재킹 공격을 막기 위해 사용
            // 클릭재킹은 사용자의 의도와 다른 작업이 수행되도록 속이는 보안 공격 기술
            .formLogin((formLogin) -> formLogin
                .loginPage("/user/login")
                .defaultSuccessUrl("/"))
            //로그아웃 기능 구현 설정
            // formLogin 메서드는 스프링 시큐리티의 로그인 설정 담당
            .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
            );
            
        return http.build();
    }

    // @Bean
    // PasswordEncoder PasswordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
