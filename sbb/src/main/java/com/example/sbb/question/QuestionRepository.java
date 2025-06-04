package com.example.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page; //페이징을 위한 클래스
import org.springframework.data.domain.Pageable; //현재 페이지와 한 페이지에 보여 줄 게시물 개수 등을 설정하여 페이징 요청을 하는 클래스
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

// @Query 애너테이션 사용
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
    Page<Question> findAll(Pageable pageable);
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    // @Query 애너테이션 사용
    @Query("select "
            + "distinct q "
            + "from Question q "
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "  q.subject like %:kw%"
            + "  or q.content like %:kw%"
            + "  or u1.username like %:kw%"
            + "  or a.content like %:kw%"
            + "  or u2.username like %:kw%")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}
