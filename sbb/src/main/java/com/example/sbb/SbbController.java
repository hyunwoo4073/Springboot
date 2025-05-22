package com.example.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SbbController {
    @GetMapping("/sbb")
    @ResponseBody
    public String hello() {
        return "안녕하세요 sbb에 오신것을 환영합니다.";
    }

    // 리다이렉트 부분
    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
    
}
