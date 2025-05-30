package com.example.sbb.answer;

import java.time.LocalDateTime;

import com.example.sbb.question.Question;
import com.example.sbb.user.SiteUser;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
   private final AnswerRepository answerRepository;

   public void create(Question question, String contet, SiteUser author) {
    Answer answer = new Answer();
    answer.setContent(contet);
    answer.setCreateDate(LocalDateTime.now());
    answer.setQuestion(question);
    answer.setAuthor(author);
    this.answerRepository.save(answer);
   }
}
