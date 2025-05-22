package com.example.sbb.answer;

import java.time.LocalDateTime;

import com.example.sbb.question.Question;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
   private final AnswerRepository answerRepository;

   public void create(Question question, String contet) {
    Answer answer = new Answer();
    answer.setContent(contet);
    answer.setCreateDate(LocalDateTime.now());
    answer.setQuestion(question);
    this.answerRepository.save(answer);
   }
}
