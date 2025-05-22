package com.example.sbb.question;

import java.util.List;

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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/question") //url 프리픽스
@RequiredArgsConstructor
@Controller
public class QuestionController {
    
    // private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    @GetMapping("/list")
    // @ResponseBody
    public String list(Model model) {
        // List<Question> questionList = this.questionRepository.findAll();
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
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
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult 
    bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
    }
    
}
