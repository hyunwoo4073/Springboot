package com.example.sbb.question;

import java.util.List;

import java.security.Principal;
import com.example.sbb.user.SiteUser;
import com.example.sbb.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable; //변하는 값을 얻을 때 사용
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sbb.answer.AnswerForm;

@RequestMapping("/question") //url 프리픽스
@RequiredArgsConstructor
@Controller
public class QuestionController {
    
    // private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    // @ResponseBody
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") 
    int page) {
        // List<Question> questionList = this.questionRepository.findAll();
        // List<Question> questionList = this.questionService.getList();
        // model.addAttribute("questionList", questionList);
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }
    
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm 
    answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    // @PostMapping("/create")
    // public String questionCreate(@RequestParam(value = "subject") String subject,
    // @RequestParam(value = "content") String content) {
    //     this.questionService.create(subject, content);
    //     return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
    // }
    
    // 폼 활용하기
    // Principal 객체를 사용하는 메서드에 @PreAuthorize("isAuthenticated()") 애너테이션 사용, 로그인한 경우에만 실행됨
    // 로그아웃 상태에서 호출되면 로그인 페이지로 강제 이동
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult 
    bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
    }
    
}
