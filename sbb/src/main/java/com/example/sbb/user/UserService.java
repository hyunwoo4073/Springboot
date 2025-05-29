package com.example.sbb.user;

import com.example.sbb.DataNotFoundException;

import java.util.Optional;

import org.springframework.security.access.method.P;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// BCryptPasswordEncoder 클래스는 비크립트 해시 함수를 사용
// 비크립트는 해시 함수의 하나로 주로 비밀번호와 같은 보안 정보를 안전하게 저장하고 검증할 때 사용
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 암호화를 위해 필요
        user.setPassword(passwordEncoder.encode(password)); // 객체를 직접 생성하여 사용하지 않고 빈으로 등록한 Password Encoder 객체를 주입받아 사용할 수 있도록 수정
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
