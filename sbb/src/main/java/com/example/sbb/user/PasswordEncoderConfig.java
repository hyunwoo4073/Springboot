package com.example.sbb.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

// 순환참조가 발생하여 기존에 SecurityConfig 클래스에 있던 것을 분리함
// 분리 후 해결됨
// 참고 : https://green-bin.tistory.com/52
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class PasswordEncoderConfig {
    @Bean
    PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
